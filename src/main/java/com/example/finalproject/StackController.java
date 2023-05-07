package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Stack;

public class StackController {
    @FXML
    private TextField nodeText;
    @FXML
    private Text prompt;
    @FXML
    private Pane pane;
    private Stack<String> stack = new Stack<>();
    private int height = 62;
    @FXML
    private void push(){
        int size = pane.getChildren().size();
//        System.out.println("size ="+size);
        if (nodeText.getText().equals("")){
            prompt.setText("ERROR!! \n Input a value on the TextField");
        } else {
            if (size==7){
                addToStack();
            }else {
                pane.getChildren().remove(size-1);
                addToStack();
            }
        }

    }
    private void addToStack() {
        String val = nodeText.getText();
        Rectangle rec = new Rectangle(342,height,50,50);
        rec.setFill(Color.GREEN);
        Label text = new Label(val);
        text.setFont(Font.font("Arial", FontWeight.BOLD,15));
        text.setLayoutX(357);
        text.setLayoutY(height+15);
        Label tos = new Label("Top of the Stack");
        tos.setFont(Font.font("Arial", FontWeight.BOLD,13));
        tos.setTextFill(Color.BLUEVIOLET);
        tos.setLayoutX(400);
        tos.setLayoutY(height+10);
        pane.getChildren().add(rec);
        pane.getChildren().add(text);
        pane.getChildren().add(tos);
        stack.push(val);
        prompt.setText("The value "+val+" is pushed");
        nodeText.clear();
        height=height+90;
    }

    @FXML
    private void pop(){
        int size = pane.getChildren().size();
        if (stack.isEmpty()){
            prompt.setText("The stack is empty");
        } else {
            pane.getChildren().remove(size-1);
            pane.getChildren().remove(size-2);
            pane.getChildren().remove(size-3);
            String val = stack.peek();
            stack.pop();
            if (!stack.isEmpty()){
                prompt.setText("The value "+val+" is popped \n with the TOS of "+stack.peek());
            } else {
                prompt.setText("The value "+val+" is popped \n with the stack being empty");
            }
            nodeText.clear();
            height=height-90;
            Label tos = new Label("Top of the Stack");
            tos.setFont(Font.font("Arial", FontWeight.BOLD,13));
            tos.setLayoutX(400);
            tos.setLayoutY(height-80);
            tos.setTextFill(Color.BLUEVIOLET);
            pane.getChildren().add(tos);
        }
    }
    @FXML
    private void top(){
        if (stack.isEmpty()){
            prompt.setText("The stack is empty");
        } else {
            String val = stack.peek();
            prompt.setText("The top value "+val);
            nodeText.clear();
        }
    }
    @FXML
    private void loadFirstPage() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
}
