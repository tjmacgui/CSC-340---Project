package csc340project.example.springio.GameListings;

import csc340project.example.springio.GameTags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Table(name = "GameListings")
public class Listing {
    //GameID int PK
    @Id
    private int listingId;

    //title TINYTEXT not null
    @Nonnull
    private String title;

    //ReleaseDate Date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date ReleaseDate;

    //Thumbnail IMAGE
    private String gameImageURL;


    //Genre VARCHAR
    private String genre;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;
    public Listing() {
    }

    public Listing(int listingId, String genre, String gameImageURL, Date releaseDate, String title) {
        this.listingId = listingId;
        this.genre = genre;
        this.gameImageURL = gameImageURL;
        ReleaseDate = releaseDate;
        this.title = title;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGameImageURL() {
        return gameImageURL;
    }

    public void setGameImageURL(String gameImageURL) {
        this.gameImageURL = gameImageURL;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
