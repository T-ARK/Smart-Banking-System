package dao;

import db.DBConnection;
import model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO {
    /**
     * Authenticates a staff member.
     * @param username The staff's username.
     * @param password The staff's password.
     * @return A Staff object if authenticated, otherwise null.
     */
    public Staff login(String username, String password) {
        String sql = "SELECT user_id, username FROM users WHERE username = ? AND password = ? AND role = 'staff'";
        Connection conn = DBConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Staff(rs.getInt("user_id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return null;
    }
}
