package com.example.matthewsms.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
            System.out.println("Connection successful");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
