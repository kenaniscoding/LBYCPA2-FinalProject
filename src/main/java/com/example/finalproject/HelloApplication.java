package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage window;
    @Override
    public void start(Stage stage) throws IOException {
        window= stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("firstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("JavaFX Data Structure Visualization");
        stage.setScene(scene);
        stage.show();
    }
    public Stage getStage(){
        return window;
    }

    public static void main(String[] args) {
        launch();
    }
}