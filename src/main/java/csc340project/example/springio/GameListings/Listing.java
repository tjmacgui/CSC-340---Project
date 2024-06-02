package csc340project.example.springio.GameListings;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Table(name = "Game-Listings")
public class Listing {
    //GameID int PK
    @Id
    private int ListingId;

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

    public Listing() {
    }

    public Listing(int listingId, String genre, String gameImageURL, Date releaseDate, @Nonnull String title) {
        ListingId = listingId;
        this.genre = genre;
        this.gameImageURL = gameImageURL;
        ReleaseDate = releaseDate;
        this.title = title;
    }

    public int getListingId() {
        return ListingId;
    }

    public void setListingId(int listingId) {
        ListingId = listingId;
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

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }
}
