package com.example.finalproject;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class WeatherController {

    @FXML
    private Label weatherLabel;
    @FXML
    private Label tempLabel;
    @FXML
    private Label descLabel;



    public void setWeatherData(String weatherData) throws ParseException {
        //parsing
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(weatherData);
        String location = (String) jsonObj.get("name");
        JSONObject main = (JSONObject) jsonObj.get("main");
        Double tempKelvin = (Double) main.get("temp");
        int tempFar = (int) Math.round(tempKelvin - 273.15) + 32;
        JSONArray weatherArr = (JSONArray) jsonObj.get("weather");
        String weatherDesc = ((JSONObject) weatherArr.get(0)).get("description").toString();
        JSONObject sysArr = (JSONObject) jsonObj.get("sys");
        String country = (String) sysArr.get("country");

        // Update the UI with the weather details
        weatherLabel.setText(String.format("%s, %s", location, country));
        tempLabel.setText(String.format("%d°F", tempFar));
        descLabel.setText(weatherDesc.substring(0, 1).toUpperCase() + weatherDesc.substring(1));;

        // Add animation effects
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), weatherLabel);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(2), tempLabel);
        slideIn.setFromY(50);
        slideIn.setToY(0);
        slideIn.play();
        ScaleTransition growIn = new ScaleTransition(Duration.seconds(2), descLabel);
        growIn.setFromX(0.5);
        growIn.setFromY(0.5);
        growIn.setToX(1);
        growIn.setToY(1);
        growIn.play();
    }
    @FXML
    private void back(ActionEvent event) {
        try {
            // create a new instance of  application class
            HelloApplication app = new HelloApplication();
            // call the start method of application
            app.start(new Stage());
            // close the current stage
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
