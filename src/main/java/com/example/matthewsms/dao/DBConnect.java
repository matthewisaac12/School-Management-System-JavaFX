package com.example.matthewsms.dao;

import java.sql.*;

public class DBConnect {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
            System.out.println("Connection successful");

            String sql = "INSERT INTO subject VALUES (NULL, 'English', 'ENG', 'Basic ENG', now(), null), (NULL, 'Science', 'SCI', 'Basic Science', now(), null)";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
