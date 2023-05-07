package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;

public class LinkedListController {
    @FXML
    private TextField nodeText;
    @FXML
    private Text prompt;
    @FXML
    private Pane pane;
    private int width = 30;
    private int height = 300;
    private boolean bool=true;
    private LinkedList<String> linkedList = new LinkedList<>();
    @FXML                //TODO fix addHead
    private void addHead(){
        int size = pane.getChildren().size();
        System.out.println("size="+size);
        String val = nodeText.getText();
        if (val.equals("")){
            prompt.setText("ERROR!! input a value");
        } else {
            if (linkedList.isEmpty()){ //the initial head
                linkedList.addFirst(val);
                prompt.setText(val+" was added to the start list");
                nodeText.clear();
                addChildren(val);
                bool=false;
            } else { //two or more adding
                linkedList.addFirst(val);
                prompt.setText(val+" was added to the start list");
                nodeText.clear();
                clearPane();
                displayList();
            }
        }
    }
    private void clearPane(){
        int size = pane.getChildren().size();
        if (size==18) {
            int value = 7;
            loopClear(value, size);
        } else{
            int value = 11;
            loopClear(value, size);
        }
    }
    private void loopClear(int value, int size){
        for (int i=1; i<size;i++){
            if (i==value){
                break;
            }else {
                pane.getChildren().remove(size-i);
            }
        }
    }
    private void displayList(){
        width = 30;
        height = 300;
        Label head = new Label("Head");
        head.setFont(Font.font("Arial", FontWeight.BOLD,13));
        head.setTextFill(Color.BLUEVIOLET);
        head.setLayoutX(width);
        head.setLayoutY(height-30);
        pane.getChildren().add(head);
        for (int i=0;i<linkedList.size();i++){
            addChildren(linkedList.get(i));
        }
    }
    private void addChildren(String val){
        Line line = new Line(width,height+30,width+100,height+30);

        Label arrow = new Label(">");
        arrow.setFont(Font.font("Arial", FontWeight.BOLD,17));
        arrow.setLayoutX(width+90);
        arrow.setLayoutY(height+20);

        Label NULL = new Label("null");
        NULL.setFont(Font.font("Arial", FontWeight.BOLD,15));
        NULL.setTextFill(Color.BLUEVIOLET);
        NULL.setLayoutX(width+100);
        NULL.setLayoutY(height+20);

        Label value = new Label(val);
        value.setFont(Font.font("Arial", FontWeight.BOLD,15));
        value.setLayoutX(width+15);
        value.setLayoutY(height+15);

        Rectangle rec = new Rectangle(width,height,50,50);
        rec.setFill(Color.GREEN);
        if (bool){
            Label head = new Label("Head");
            head.setFont(Font.font("Arial", FontWeight.BOLD,13));
            head.setTextFill(Color.BLUEVIOLET);
            head.setLayoutX(width);
            head.setLayoutY(height-30);

            pane.getChildren().add(line);
            pane.getChildren().add(arrow);
            pane.getChildren().add(rec);
            pane.getChildren().add(value);
            pane.getChildren().add(head);
            bool=false;
        }else {
            pane.getChildren().add(line);
            pane.getChildren().add(arrow);
            pane.getChildren().add(rec);
            pane.getChildren().add(value);
        }

        pane.getChildren().add(NULL);
        width=width+100;
    }
    @FXML                 //TODO fix addEnd
    private void addEnd(){
//        int size = pane.getChildren().size();
//        System.out.println("size="+size);
        String val = nodeText.getText();
        if (val.equals("")){
            prompt.setText("ERROR!! input a value");
        } else {
            if (linkedList.isEmpty()){ //the initial head
                linkedList.addLast(val);
                prompt.setText(val+" was added to the end list");
                nodeText.clear();
                addChildren(val);
            } else { //two or more adding
                linkedList.addLast(val);
                prompt.setText(val+" was added to the end list");
                nodeText.clear();
                clearPane();
                displayList();
            }
        }
    }
    @FXML
    private void deleteHead(){
        if (linkedList.isEmpty()){
            prompt.setText("ERROR!! \n list is empty");
        } else {
            String val = linkedList.getLast();
            linkedList.removeFirst();
            prompt.setText(val+" was removed at the end list \n and replaced with "+linkedList.peekFirst());
            if (linkedList.isEmpty()){
                bool=true;
                width=30;
                clearAtStart();
            } else {
                clearPane();
                displayList();
            }

        }
    }
    private void clearAtStart(){
        int size = pane.getChildren().size();
        for (int i=1; i<size; i++){
            if (size-i == 11){
                break;
            } else {
                pane.getChildren().remove(size-i);
            }
        }
    }
    @FXML
    private void deleteEnd(){
//        int size = pane.getChildren().size();
        if (linkedList.isEmpty()){
            prompt.setText("ERROR!! \n list is empty");
        } else {
            String val = linkedList.getFirst();
            linkedList.removeLast();
            if (linkedList.isEmpty()){
                bool=true;
                width=30;
                clearAtStart();
            } else {
                clearPane();
                displayList();
                width=width-100;
            }
            prompt.setText(val+" was removed at the end list \n and replaced with "+linkedList.peekLast());
        }
    }

    /**
     * TODO
     *  if linkedlist contains size == 1 then
     *  remove this specific number of nodes in pane
     */
    @FXML
    private void addBetween(){
        String[] prt=getMiddleString();
        if (prt.length==1){
            prompt.setText("ERROR!! input a value");
        }else {
            String element = prt[0];
            int index = Integer.parseInt(prt[1]);
            if (linkedList.isEmpty()){
                linkedList.add(index,element);
                prompt.setText(element+" was added to the index "+index);
                displayList();
            } else if (linkedList.size()==1){
                linkedList.add(index,element);
                prompt.setText(element+" was added to the index "+index);
                clearAtStart();
                displayList();
            } else {
                linkedList.add(index,element);
                prompt.setText(element+" was added to the index "+index);
                nodeText.clear();
                clearPane();
                displayList();
            }
            nodeText.clear();
        }
    }
    @FXML
    private void deleteBetween(){
//        int size = pane.getChildren().size();
//        System.out.println("size="+size);
        if (linkedList.isEmpty()){
            prompt.setText("ERROR!! \n list is empty");
        } else{
            int index = Integer.parseInt(nodeText.getText());
            //delete line 56 if doesnt work
            String item = linkedList.get(index);
            if (linkedList.size()==1){
                deleteHead();
                prompt.setText(item+" was deleted at the index "+index);
            } else {
                if (index==0){
                    deleteHead();
                    prompt.setText(item+" was deleted at the index "+index);
                } else if (index==linkedList.size()-1){
                    deleteEnd();
                    prompt.setText(item+" was deleted at the index "+index);
                }else {
                    linkedList.remove(index);
                    prompt.setText(item+" was deleted at the index "+index);
                    nodeText.clear();
                    clearPane();
                    displayList();
                }
            }
            nodeText.clear();
        }
    }
    private String[] getMiddleString(){
        String val = nodeText.getText();
        return val.split(",");
    }
    @FXML
    private void loadFirstPage() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }
}
