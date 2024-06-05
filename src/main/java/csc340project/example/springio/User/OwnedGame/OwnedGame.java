package csc340project.example.springio.User.OwnedGame;

import com.fasterxml.jackson.annotation.JsonBackReference;
import csc340project.example.springio.User.User;
import jakarta.persistence.*;

@Table(name = "owned_game")
@Entity
public class OwnedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    /**
    @ManyToOne
    @JoinColumn(name = "listing_id")
    @JsonBackReference
    private Listing gameListing;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
 **/
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

/**
    public Listing getGameListing() {
        return gameListing;
    }

    public void setGameListing(Listing gameListing) {
        this.gameListing = gameListing;
    }
**/
}
