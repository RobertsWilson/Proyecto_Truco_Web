// language: java
package com.trucoweb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = System.getenv().getOrDefault("DB_URL",
            "jdbc:mysql://localhost:3306/trucoweb?useSSL=false&serverTimezone=UTC");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "root");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
