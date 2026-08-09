package com.example.matthewsms.dao;

import com.example.matthewsms.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DBConnect {

    public static Connection connect() {
        Connection connection;
        try {
            return connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
