package com.example.finalproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        VBox root = fxmlLoader.load();

        Scene scene = new Scene(root, 900, 650);
        stage.setTitle("WeatherApp By Momin Imran");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("cloudslogo.png")));
        stage.setMinHeight(650);
        stage.setMinWidth(720);
        stage.setScene(scene);

        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}