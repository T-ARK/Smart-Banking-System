package dao;

import db.DBConnection;
import model.Admin;
import model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    /**
     * Authenticates an admin user.
     * @param username The admin's username.
     * @param password The admin's password.
     * @return An Admin object if authenticated, otherwise null.
     */
    public Admin login(String username, String password) {
        String sql = "SELECT user_id, username FROM users WHERE username = ? AND password = ? AND role = 'admin'";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(rs.getInt("user_id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return null;
    }

    /**
     * Creates a new staff member.
     * @param username The new staff's username.
     * @param password The new staff's password.
     * @return true if the staff member was created successfully, false otherwise.
     */
    public boolean addStaff(String username, String password) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'staff')";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return false;
    }

    /**
     * Retrieves a list of all staff members.
     * @return A list of Staff objects.
     */
    public List<Staff> viewStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT user_id, username FROM users WHERE role = 'staff'";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return staffList;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                staffList.add(new Staff(rs.getInt("user_id"), rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return staffList;
    }
}
