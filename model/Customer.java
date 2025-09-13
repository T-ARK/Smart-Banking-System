package model;

public class Customer {
    private int userId;
    private String username;

    public Customer(int userId, String username) {
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
