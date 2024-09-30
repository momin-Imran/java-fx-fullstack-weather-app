module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.json;
    requires json.simple;
    requires java.desktop;
    requires javafx.media;
    requires javafx.graphics;


    opens com.example.finalproject to javafx.fxml;
    exports com.example.finalproject;
}