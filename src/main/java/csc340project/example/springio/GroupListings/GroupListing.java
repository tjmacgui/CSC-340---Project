package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameTags.Tag;
import csc340project.example.springio.GameTags.TagService;
import csc340project.example.springio.GroupMember.GroupMember;
import csc340project.example.springio.User.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "group_listings")
public class GroupListing {
    private static final int MINMEMBERCOUNT = 2;
    private static final int MAXMEMBERCOUNT = 12;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupListingId;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listingId; //foreign key to game listing

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User ownerId;

    @Nonnull
    private String title; //group title is a nonnull

    @Nonnull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date listingPostDate; //date when listing was posted

    private List<String> availableTags; //list of available tags to this group listing based on it's game

    private List<String> tags; //list of descriptive tags that apply to this group listing

    private String description; //group listing description

    // @DateTimeFormat()
    // private LocalDateTime startDateTime; //group start date and time

    // @DateTimeFormat()
    // private LocalDateTime endDateTime; //group start date and time

    private int maxNumMembers = MINMEMBERCOUNT; //defines the maximum number of members in a group

    private int openMemberSpots = maxNumMembers - 1; //defines the number of open spots in a group, default value accounts for owner

    @OneToMany(mappedBy = "groupListing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupMember> members;

    public GroupListing() {}

    public GroupListing(@Nonnull String title, int groupListingId, Listing listingId, User ownerId, @Nonnull Date listingPostDate, String description, int maxNumMembers, List<String> tags) {
        this.title = title;
        this.groupListingId = groupListingId;
        this.listingId = listingId;
        this.ownerId = ownerId;
        this.listingPostDate = listingPostDate;
        tagInit(this.listingId.getListingId());
        this.tags = tags;
        this.description = description;
        //this.startDateTime = startDateTime;
        if (maxNumMembers >= MINMEMBERCOUNT && maxNumMembers <= MAXMEMBERCOUNT)
            this.maxNumMembers = maxNumMembers;
        //this.endDateTime = endDateTime;
        this.openMemberSpots = this.maxNumMembers - 1; //accounts for group lister as a member
    }

    /**
     * Initializes availableTags list by querying tags belonging to the provided listingId on Group Listing creation.
     * @param listingId game id that the group belongs to
     */
    private void tagInit(int listingId) {
        List<String> returnedListing = GroupListingService.getTagListForGame(listingId);
        if (returnedListing.isEmpty()) {
            throw new EntityNotFoundException("While processing tagInit no existing tags where found relating to the game with an ID of " + listingId);
        } else {
            for (String tag : returnedListing) {
                this.availableTags.add(tag);
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

    public int getOpenMemberSpots() {
        return openMemberSpots;
    }

    public void setOpenMemberSpots(int openMemberSpots) {
        this.openMemberSpots = openMemberSpots;
    }

    public int getGroupListingId() {
        return groupListingId;
    }

    public void setGroupListingId(int groupListingId) {
        this.groupListingId = groupListingId;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    public Listing getlistingId() {
        return listingId;
    }

    public void setlistingId(Listing listingId) {
        this.listingId = listingId;
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

    // public LocalDateTime getStartDateTime() {
    //     return startDateTime;
    // }

    // public void setStartDateTime(LocalDateTime startDateTime) {
    //     this.startDateTime = startDateTime;
    // }

    // public LocalDateTime getEndDateTime() {
    //     return endDateTime;
    // }

    // public void setEndDateTime(LocalDateTime endDateTime) {
    //     this.endDateTime = endDateTime;
    // }

    public int getMaxNumMembers() {
        return maxNumMembers;
    }

    public void setMaxNumMembers(int maxNumMembers) {
        this.maxNumMembers = maxNumMembers;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }
}
