package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // MySQL database credentials
    // You MUST change these to your actual database URL, username, and password.
    private static final String URL = "jdbc:mysql://localhost:3306/SmartBankingSystem";
    private static final String USER = "YOUR_DB_USER";
    private static final String PASSWORD = "YOUR_DB_PASSWORD";

    /**
     * Establishes and returns a database connection.
     *
     * @return A valid Connection object if successful, otherwise null.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Closes the given database connection.
     *
     * @param connection The Connection object to close.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

