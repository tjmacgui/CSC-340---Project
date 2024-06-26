package csc340project.example.springio.User.FavoriteGame;

import csc340project.example.springio.User.User;
import jakarta.persistence.*;

@Table(name = "favorite_game")
@Entity
public class FavoriteGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
/** TODO: waiting on game listing
    @ManyToOne
    @JoinColumn(name = "game_listing_id")
    private GameListing gameListing;
**/
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
/** TODO: waiting on game listing
    public GameListing getGameListing() {
        return gameListing;
    }

    public void setGameListing(GameListing gameListing) {
        this.gameListing = gameListing;
    }
**/
}
