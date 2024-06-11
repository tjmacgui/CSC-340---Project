package csc340project.example.springio.User;

import csc340project.example.springio.User.BannedAccount.BannedAccount;
import csc340project.example.springio.User.Friend.Friend;
import csc340project.example.springio.User.LinkedAccount.LinkedAccount;
import csc340project.example.springio.User.OwnedGame.OwnedGame;
import csc340project.example.springio.User.UserRating.UserRating;
import csc340project.example.springio.User.UserReport.UserReport;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 15, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    private Integer profileImageId;

    private java.sql.Date profileCreationDate;

    private boolean isFlagged;

    //TODO: leader ranking and thumbs up

    private int leaderRanking;
    private int thumbsUp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LinkedAccount> linkedAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRating> userRatings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserReport> userReports;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friend> friends;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BannedAccount> bannedAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OwnedGame> ownedGames;

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

    public java.sql.Date getProfileCreationDate() {
        return profileCreationDate;
    }

    public void setProfileCreationDate(java.sql.Date profileCreationDate) {
        this.profileCreationDate = profileCreationDate;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public List<LinkedAccount> getLinkedAccounts() {
        return linkedAccounts;
    }

    public void setLinkedAccounts(List<LinkedAccount> linkedAccounts) {
        this.linkedAccounts = linkedAccounts;
    }

    public List<UserRating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<UserRating> userRatings) {
        this.userRatings = userRatings;
    }

    public List<UserReport> getUserReports() {
        return userReports;
    }

    public void setUserReports(List<UserReport> userReports) {
        this.userReports = userReports;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<BannedAccount> getBannedAccounts() {
        return bannedAccounts;
    }

    public void setBannedAccounts(List<BannedAccount> bannedAccounts) {
        this.bannedAccounts = bannedAccounts;
    }

    public int getLeaderRanking() {
        return leaderRanking;
    }

    public void setLeaderRanking(int leaderRanking) {
        this.leaderRanking = leaderRanking;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public List<OwnedGame> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(List<OwnedGame> ownedGames) {
        this.ownedGames = ownedGames;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


}