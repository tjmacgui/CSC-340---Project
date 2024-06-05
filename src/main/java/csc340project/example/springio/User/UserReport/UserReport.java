package csc340project.example.springio.User.UserReport;

import com.fasterxml.jackson.annotation.JsonBackReference;
import csc340project.example.springio.User.User;
import jakarta.persistence.*;

@Table(name = "user_report")
@Entity
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private String reportDetails;
    private java.sql.Date reportDate;

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

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public java.sql.Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(java.sql.Date reportDate) {
        this.reportDate = reportDate;
    }
}
