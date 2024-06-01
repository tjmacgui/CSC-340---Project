package csc340project.example.springio.GameTags;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GameTags")
public class Tag {
    @ManyToOne
    @JoinColumn(name = "game_id")
    public int gameId; //foreign key that references the game that the tag belongs to

    @Nonnull
    public String tagName; //describes the tag for users to view on groups, such as "Newbie Friendly"

    public Tag() {}

    public Tag(int gameId, @Nonnull String tagName) {
        this.gameId = gameId;
        this.tagName = tagName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Nonnull
    public String getTagName() {
        return tagName;
    }

    public void setTagName(@Nonnull String tagName) {
        this.tagName = tagName;
    }
}
