package csc340project.example.springio.GroupMember;

import csc340project.example.springio.GroupListings.GroupListing;
import csc340project.example.springio.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Group Members")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_listing_id")
    private GroupListing groupListing;          //foreign key referencing the group listing this member belongs to

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;             //foreign key referencing the group members user ID

    private boolean isReady = false;        //signifies if member is ready or not

    public GroupMember(GroupListing groupListing, User userId) {
        this.groupListing = groupListing;
        this.userId = userId;
    }

    public GroupListing getGroupListing() {
        return groupListing;
    }

    public void setGroupListing(GroupListing groupListing) {
        this.groupListing = groupListing;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
