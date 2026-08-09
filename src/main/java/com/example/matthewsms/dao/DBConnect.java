package com.example.matthewsms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
            System.out.println("Connection successful");

            String sql = "SELECT * FROM subject";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
            System.out.println(resultSet.getString("subject_name"));
            System.out.println(resultSet.getString("subject_code"));
            System.out.println(resultSet.getString("description"));

            System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
