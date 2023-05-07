package com.example.finalproject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class QueueController {
    @FXML
    private HBox queuePane;

    @FXML
    private TextField inputField;

    private Queue<String> queue = new LinkedList<>();

    @FXML
    public void enqueue() {
        // Get the value from the input field
        String value = inputField.getText();

        // Check if the input field is empty
        if (value.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enter a VALUE!!");
            alert.showAndWait();
        }
        else {
            // Add the value to the queue
            queue.add(value);

            // Create a new Label object with the value
            Label box = new Label(value);
            box.getStyleClass().add("box"); // Apply the "box" style to the label

            // Add the Label object to the queuePane HBox
            queuePane.getChildren().add(box);

            // Clear the input field
            inputField.clear();
        }
    }

    @FXML
    public void dequeue() {
        // Check if the queue is empty
        if (queue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The Queue is empty!! ");
            alert.showAndWait();
        }
        else {
            // Remove the first value from the queue
            String removedValue = queue.remove();

            // Remove the first box from the queuePane HBox
            Label box = (Label) queuePane.getChildren().get(0);
            queuePane.getChildren().remove(0);

            // Clear the value of the removed box
            box.setText("");

            // Reposition the remaining boxes in the center of the queuePane HBox
            for (Node node : queuePane.getChildren()) {
                HBox.setMargin(node, new Insets(0, 10, 0, 10));
            }
        }
    }

    @FXML
    public void printFront() {
        // Check if the queue is empty
        if (queue.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The Queue is empty!! ");
            alert.showAndWait();
        }
        else {
            // Get the first value in the queue
            String frontValue = queue.peek();

            // Show the value of the first box on the AnchorPane
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The front value of the queue is: " + frontValue);
            alert.showAndWait();
        }
    }


    @FXML
    private void loadFirstPage() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }

}