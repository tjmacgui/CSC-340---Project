package csc340project.example.springio.GameTags;

import csc340project.example.springio.GameListings.Listing;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "GameTags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    public Listing listing; //foreign key that references the game that the tag belongs to

    @Nonnull
    public String tagName; //describes the tag for users to view on groups, such as "Newbie Friendly"


    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    @Nonnull
    public String getTagName() {
        return tagName;
    }

    public void setTagName(@Nonnull String tagName) {
        this.tagName = tagName;
    }
}
