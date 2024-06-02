package csc340project.example.springio.admin;


import jakarta.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "AdminLogin")
public class SysAdmin {

    @Id
    private int AdminID;

    @Nonnull
    private String AdminPassword;

    public SysAdmin() {
    }

    public SysAdmin(int adminID, @Nonnull String adminPassword) {
        AdminID = adminID;
        AdminPassword = adminPassword;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }

    @Nonnull
    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(@Nonnull String adminPassword) {
        AdminPassword = adminPassword;
    }
}
