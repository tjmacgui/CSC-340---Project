package csc340project.example.springio.GroupMember;

import jakarta.persistence.*;

@Entity
@Table(name = "Group Members")
public class GroupMember {
    @ManyToOne
    @JoinColumn(name = "listing_id")
    private int listingId;          //foreign key referencing the group listing this member belongs to

    @ManyToOne
    @JoinColumn(name = "user_id")
    private int userId;             //foreign key referencing the group members user ID

    private boolean isReady;        //signifies if member is ready or not

    public GroupMember() {}

    public GroupMember(int userId, boolean isReady, int listingId) {
        this.userId = userId;
        this.isReady = isReady;
        this.listingId = listingId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
