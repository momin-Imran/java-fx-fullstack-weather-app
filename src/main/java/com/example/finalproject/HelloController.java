package com.example.finalproject;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button submitButton;
    @FXML
    private TextField locationText;


    private Stage stage;

    public HelloController() {

    }

    public HelloController(Stage stage) {
        this.stage = stage;
        welcomeText.setOpacity(1.0);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static String getWeatherData(String location) throws IOException {
        String apiKey = "c3941df922d0b5f716596a6dd91f47d3";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString()); // print response string
            return response.toString();
        } else {
            return null;
        }
    }

    @FXML
    protected void onHelloButtonClick() {
       // welcomeText.setText("Welcome to Weather App by Momin Imran");
        String location = locationText.getText();
        if (location != null && !location.isEmpty()) {
            try {


    //loadingIndicator.setVisible(true);
                String weatherData = getWeatherData(location);
                if (weatherData != null) {
                    // Play the sound file
                    Media sound = new Media(new File("C:\\Users\\momin\\Desktop\\HCC\\Semester 5\\JAVA II\\finalProject\\src\\main\\resources\\com\\example\\finalproject\\reverse-piano-3-note-tune-96852.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    MediaView mediaView = new MediaView(mediaPlayer);
                    mediaPlayer.play();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("weather.fxml"));
                    VBox newRoot = loader.load();



                    WeatherController controller = loader.getController();
                    controller.setWeatherData(weatherData);




                    // create a fade out transition for the old view
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(250), welcomeText.getParent());
                    fadeOut.setFromValue(1.0);
                    fadeOut.setToValue(0.94);
                    //loadingIndicator.setVisible(true); //load on

                    // create a fade in transition for the new view
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(750), newRoot);
                    fadeIn.setFromValue(0.94);
                    fadeIn.setToValue(1.0);

                    // play the fade out and fade in transitions in sequence
                    SequentialTransition transition = new SequentialTransition(fadeOut, fadeIn);
                    transition.setOnFinished(event -> stage.getScene().setRoot(newRoot));
                    transition.play();
                } else {
                    // display an error alert
                   // loadingIndicator.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No data could be fetched for this location, please try again.");
                    alert.showAndWait();
                }
            } catch (IOException | ParseException e) {
               // loadingIndicator.setVisible(false);
                e.printStackTrace();
            }
        } else {
            //loadingIndicator.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a location");
            alert.showAndWait();
        }



    }

    public void initialize() {
        locationText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    onHelloButtonClick();
                }
            }
        });


    }







}