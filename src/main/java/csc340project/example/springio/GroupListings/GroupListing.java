package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameTags.Tag;
import csc340project.example.springio.GameTags.TagService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GroupListings")
public class GroupListing {
    @Autowired
    TagService tagService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listingId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private int gameId; //foreign key to game listing

    @Nonnull
    private String title; //group title is a nonnull

    @Nonnull
    private Date listingPostDate; //date when listing was posted

    /*todo: figure out how this should be implemented, should pull from game tags dependent table
    *  not sure how it will translate to sql entries yet. maybe should be defined in a service class*/
    private List<String> availableTags; //list of available tags to this group listing based on it's game

    private List<String> tags; //list of descriptive tags that apply to this group listing

    private String description; //group listing description

    private LocalDateTime startDateTime; //group start date and time

    private LocalDateTime endDateTime; //group start date and time

    private int maxNumMembers; //defines the maximum number of members in a group

    private int openMemberSpots; //defines the number of open spots in a group

    public GroupListing() {}

    public GroupListing(@Nonnull String title, int listingId, int gameId, @Nonnull Date listingPostDate, String description, LocalDateTime startDateTime, int maxNumMembers, LocalDateTime endDateTime, String tags) {
        this.title = title;
        this.listingId = listingId;
        this.gameId = gameId;
        this.listingPostDate = listingPostDate;
        tagInit(gameId);
        selectedTagsInit(tags);
        this.description = description;
        this.startDateTime = startDateTime;
        this.maxNumMembers = maxNumMembers;
        this.endDateTime = endDateTime;
        this.openMemberSpots = maxNumMembers - 1; //accounts for group lister as a member
    }

    /**
     * Initializes availableTags list by querying tags belonging to the provided gameId on Group Listing creation.
     * @param gameId game id that the group belongs to
     */
    private void tagInit(int gameId) {
        List<Tag> returnedListing = tagService.getAllTagsForGame(gameId);
        if (returnedListing.isEmpty()) {
            throw new EntityNotFoundException("While processing tagInit no existing tags where found relating to the game with an ID of " + gameId);
        } else {
            for (Tag tag : returnedListing) {
                this.availableTags.add(tag.getTagName());
            }
        }
    }

    /**
     * Initializes tags list by parsing a string full of tag names and adding those tags if they are listed in availableTags.
     * @param tagsString string of tag names separated by ';'
     */
    private void selectedTagsInit(String tagsString) {
        if (tagsString.isEmpty())
            return;

        String[] tagNames = tagsString.split(";", 0);
        for (String tagName : tagNames) {
            if (this.availableTags.contains(tagName)) {
                this.tags.add(tagName);
            }
        }
    }

    public void memberJoin() {
        this.openMemberSpots--;
    }

    public void memberLeaves() {
        this.openMemberSpots++;
    }
}
