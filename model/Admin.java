package model;

public class Admin {
    private int userId;
    private String username;

    public Admin(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
