package com.example.matthewsms.dao;

import com.example.matthewsms.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DBConnect {

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
        return connection;
    }
}
