package csc340project.example.springio.GameListings;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import csc340project.example.springio.GameTags.Tag;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GameListings")
public class Listing {
    //GameID int PK
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int listingId;

    //steam id
    private int steamId;

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
//    int listingId,
    public Listing(int steamId, String genre, String gameImageURL, Date releaseDate, String title) {
        this.steamId = steamId;
//        this.listingId = listingId;
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

    public int getSteamId() {
        return steamId;
    }

    public void setSteamId(int steamId) {
        this.steamId = steamId;
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
