package csc340project.example.springio.User.OwnedGame;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.User.User;
// TODO: waiting on game listing import csc340project.example.springio.GameListing.GameListing;
import jakarta.persistence.*;

@Entity
public class OwnedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ListingId")
    private Listing ListingId;


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Listing getListingId() {
        return ListingId;
    }

    public void setListingId(Listing listingId) {
        this.ListingId = listingId;
    }

}
