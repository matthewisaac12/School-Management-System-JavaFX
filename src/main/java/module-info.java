module com.example.matthewsms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.matthewsms to javafx.fxml;
    exports com.example.matthewsms;
}