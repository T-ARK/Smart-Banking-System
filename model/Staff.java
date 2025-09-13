package model;

public class Staff {
    private int userId;
    private String username;

    public Staff(int userId, String username) {
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
