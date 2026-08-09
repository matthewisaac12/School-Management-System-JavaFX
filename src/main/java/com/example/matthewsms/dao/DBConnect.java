package com.example.matthewsms.dao;

import com.example.matthewsms.entity.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/matthew_school_management", "root", "");
            System.out.println("Connection successful");
            
            Subject subject = new Subject();
            subject.setSubjectCode("GEO");
            subject.setSubjectName("Geography");
            subject.setDescription("Basic Geo");

            String sql = "INSERT INTO subject VALUES (null, ?, ?, ?, now(), now())";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getSubjectName());
            ps.setString(2, subject.getSubjectCode());
            ps.setString(3, subject.getDescription());

            ps.executeUpdate();
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
