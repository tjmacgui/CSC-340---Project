package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameTags.Tag;
import csc340project.example.springio.GameTags.TagService;
import csc340project.example.springio.GroupMember.GroupMember;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Group Listings")
public class GroupListing {
    private static final int MINMEMBERCOUNT = 2;
    private static final int MAXMEMBERCOUNT = 12;

    @Autowired
    TagService tagService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listingId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private int gameId; //foreign key to game listing

    @ManyToOne
    @JoinColumn(name = "users")
    private int ownerId;

    @Nonnull
    private String title; //group title is a nonnull

    @Nonnull
    private Date listingPostDate; //date when listing was posted

    private List<String> availableTags; //list of available tags to this group listing based on it's game

    private List<String> tags; //list of descriptive tags that apply to this group listing

    private String description; //group listing description

    private LocalDateTime startDateTime; //group start date and time

    private LocalDateTime endDateTime; //group start date and time

    private int maxNumMembers = MINMEMBERCOUNT; //defines the maximum number of members in a group

    private int openMemberSpots = maxNumMembers - 1; //defines the number of open spots in a group, default value accounts for owner

    @OneToMany(mappedBy = "GroupListings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> members;

    public GroupListing() {}

    public GroupListing(@Nonnull String title, int listingId, int gameId, int ownerId, @Nonnull Date listingPostDate, String description, LocalDateTime startDateTime, int maxNumMembers, LocalDateTime endDateTime, String tags) {
        this.title = title;
        this.listingId = listingId;
        this.gameId = gameId;
        this.ownerId = ownerId;
        this.listingPostDate = listingPostDate;
        tagInit(gameId);
        selectedTagsInit(tags);
        this.description = description;
        this.startDateTime = startDateTime;
        if (maxNumMembers >= MINMEMBERCOUNT && maxNumMembers <= MAXMEMBERCOUNT)
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

    public TagService getTagService() {
        return tagService;
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public int getOpenMemberSpots() {
        return openMemberSpots;
    }

    public void setOpenMemberSpots(int openMemberSpots) {
        this.openMemberSpots = openMemberSpots;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Nonnull
    public Date getListingPostDate() {
        return listingPostDate;
    }

    public void setListingPostDate(@Nonnull Date listingPostDate) {
        this.listingPostDate = listingPostDate;
    }

    public List<String> getAvailableTags() {
        return availableTags;
    }

    public void setAvailableTags(List<String> availableTags) {
        this.availableTags = availableTags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getMaxNumMembers() {
        return maxNumMembers;
    }

    public void setMaxNumMembers(int maxNumMembers) {
        this.maxNumMembers = maxNumMembers;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
