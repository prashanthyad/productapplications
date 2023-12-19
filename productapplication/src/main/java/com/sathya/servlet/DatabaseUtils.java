package com.sathya.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    public static Connection createConnection() throws SQLException {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            String dbURL = "jdbc:mysql://localhost:8801/prashanth";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here or propagate them
            throw new SQLException("Error establishing connection", e);
        }
        return connection;
    }
}
