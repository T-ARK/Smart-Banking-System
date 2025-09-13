package dao;

import db.DBConnection;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    /**
     * Authenticates a customer.
     * @param username The customer's username.
     * @param password The customer's password.
     * @return A Customer object if authenticated, otherwise null.
     */
    public Customer login(String username, String password) {
        String sql = "SELECT user_id, username FROM users WHERE username = ? AND password = ? AND role = 'customer'";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("user_id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return null;
    }

    /**
     * Registers a new customer.
     * @param username The new customer's username.
     * @param password The new customer's password.
     * @return The newly created Customer object with their user_id, or null if registration failed.
     */
    public Customer register(String username, String password) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'customer')";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        return new Customer(userId, username);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return null;
    }

    /**
     * Retrieves a list of all customers.
     * @return A list of Customer objects.
     */
    public List<Customer> viewCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT user_id, username FROM users WHERE role = 'customer'";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return customerList;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customerList.add(new Customer(rs.getInt("user_id"), rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return customerList;
    }
}
