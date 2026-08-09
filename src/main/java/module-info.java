module com.example.matthewsms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.matthewsms to javafx.fxml;
    opens com.example.matthewsms.controller;
    exports com.example.matthewsms;
}