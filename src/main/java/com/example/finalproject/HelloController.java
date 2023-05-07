package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private void loadBST() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BST.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
    @FXML
    private void loadStack() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stack.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
    @FXML
    private void loadQueue() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Queue.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
    @FXML
    private void loadLinkedList() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LinkedList.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
}