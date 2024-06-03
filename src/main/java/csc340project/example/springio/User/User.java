package csc340project.example.springio.User;

import csc340project.example.springio.User.BannedAccount.BannedAccount;
import csc340project.example.springio.User.FavoriteGame.FavoriteGame;
import csc340project.example.springio.User.Friend.Friend;
import csc340project.example.springio.User.LinkedAccount.LinkedAccount;
import csc340project.example.springio.User.OwnedGame.OwnedGame;
import csc340project.example.springio.User.UserRating.UserRating;
import csc340project.example.springio.User.UserReport.UserReport;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    //For User
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 15, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    private Integer profileImageId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date profileCreationDate;

    @Column(nullable = false)
    private Boolean isFlagged;

    //For other tables
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavoriteGame> favoriteGames;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OwnedGame> ownedGames;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friend> friends;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRating> userRatings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LinkedAccount> linkedAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BannedAccount> bannedAccounts;

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserReport> reportsMade;

    @OneToMany(mappedBy = "reported", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserReport> reportsReceived;

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(Integer profileImageId) {
        this.profileImageId = profileImageId;
    }

    public Date getProfileCreationDate() {
        return profileCreationDate;
    }

    public void setProfileCreationDate(Date profileCreationDate) {
        this.profileCreationDate = profileCreationDate;
    }

    public Boolean getIsFlagged() {
        return isFlagged;
    }

    public void setIsFlagged(Boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

/**  waiting on game listing
    public List<FavoriteGame> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<FavoriteGame> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public List<OwnedGame> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(List<OwnedGame> ownedGames) {
        this.ownedGames = ownedGames;
    }
 **/
    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<UserRating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<UserRating> userRatings) {
        this.userRatings = userRatings;
    }

    public List<LinkedAccount> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<LinkedAccount> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public List<BannedAccount> getBannedAccounts() {
        return bannedAccounts;
    }

    public void setBannedAccounts(List<BannedAccount> bannedAccounts) {
        this.bannedAccounts = bannedAccounts;
    }

    public List<UserReport> getReportsMade() {
        return reportsMade;
    }

    public void setReportsMade(List<UserReport> reportsMade) {
        this.reportsMade = reportsMade;
    }

    public List<UserReport> getReportsReceived() {
        return reportsReceived;
    }

    public void setReportsReceived(List<UserReport> reportsReceived) {
        this.reportsReceived = reportsReceived;
    }

}
