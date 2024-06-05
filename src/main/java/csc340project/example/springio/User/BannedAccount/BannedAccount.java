package csc340project.example.springio.User.BannedAccount;

import com.fasterxml.jackson.annotation.JsonBackReference;
import csc340project.example.springio.User.User;
import jakarta.persistence.*;

@Table(name = "banned-account")
@Entity
public class BannedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
