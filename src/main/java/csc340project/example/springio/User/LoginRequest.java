package csc340project.example.springio.User;

/**
 * This handles just user login information
 * this way extra user information wouldnt be exposed during login
 * that information would be unnecessary anyway
 */
public class LoginRequest {
    private String username;
    private String password;

    // Getters and Setters
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
}
