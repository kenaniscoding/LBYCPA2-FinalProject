package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class TreeController {
    @FXML
    private TextField nodeText;
    @FXML
    private Pane circlePane;
    private TreeNode root;
    @FXML
    private Text prompt;
    private double vGap = 50.0;
    private class TreeNode { // Node Class of the Tree
        private TreeNode left;
        private TreeNode right;
        private int data;
        public TreeNode(int data) {
            left = null;
            right = null;
            this.data = data;
        }
    }

    public TreeController(){ // constructor
        root = null;
    }
    @FXML
    private void addCircle() { // ADD Button's Method
        if (nodeText.getText().equals("")){
            prompt.setText("ERROR! input an integer");
        } else {
            int nodeNum = Integer.parseInt(nodeText.getText());
            insert(nodeNum);
        }
    }
    public void insert (int numberC) { // insert ONE node
        double dx = this.circlePane.getWidth()/2;
        double dy = this.vGap;
        root = insertRec(root, numberC, dx, dy, this.circlePane.getWidth()/4.0);
    }
    private TreeNode insertRec (TreeNode root, int d, double dx, double dy, double hGap) {
        // insert ONE node method with recursive method
        if (root == null) {
            root = new TreeNode(d);
            Label text = new Label(nodeText.getText());
            text.setFont(Font.font("Arial", FontWeight.BOLD,13));
            text.setLayoutX((dx-10));
            text.setLayoutY((dy-10));
            Circle circle = new Circle(dx,dy,20);
            circle.setFill(Color.PINK);
            circle.setStroke(Color.BLACK);
            circlePane.getChildren().add(circle);
            circlePane.getChildren().add(text);
            nodeText.clear();
            prompt.setText("The node number "+d+" is added");
        }
        // else traverse down the list
        else if (d < root.data) {
            // add a new line when going to a new node
            circlePane.getChildren().add(new Line(dx - hGap, dy + this.vGap, dx, dy));
            // recursive method
            root.left = insertRec(root.left, d, dx - hGap, dy + this.vGap, hGap / 2.0);
        } else if (d > root.data) {
            // add a new line when going to a new node
            circlePane.getChildren().add(new Line(dx + hGap, dy + this.vGap, dx, dy));
            // recursive method
            root.right = insertRec(root.right, d,dx + hGap, dy + this.vGap, hGap / 2.0);
        }
        return root;
    }

    /**
     * When deleting a node, do the following in order
     * 1. clear the Pane
     * 2. delete the node
     * 3. display the Binary Search Tree
     */

    @FXML
    private void deleteCircle() { // DELETE button's method
        if (nodeText.getText().equals("")){
            prompt.setText("ERROR!! input an integer");
        } else {
            int numCircles = circlePane.getChildren().size();
            int index = Integer.parseInt(nodeText.getText());
            // step 1 clear the Pane
            for (int i =1; i<numCircles; i++){
                circlePane.getChildren().remove(numCircles-i);
                if (circlePane.getChildren().size()==7){
                    break;
                }
            }
            nodeText.clear();
            // step 2 delete the node
            delete(index);
            // step 3 display the Binary Search Tree
            displayTree(root);
            prompt.setText("The node number "+index+" is deleted");
        }

    }
    /**
     * Step 2's method
     */
    public void delete(int key) {
        root = deleteNode(root, key);
    }
    /**
     * Step 2.1 find the node to delete then make it null
     */
    private TreeNode deleteNode(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.data) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.data) {
            node.right = deleteNode(node.right, key);
        } else {
            //node is the one we want to delete
            if (node == root && node.left == null && node.right == null) {
                // node is the root and has no children, just return null to remove it
                node = null;
            } else if (node.left == null && node.right == null) {
                // node has no children, just return null to remove it
                node = null;
            } else if (node.left == null) {
                // node has one child (right), return right child to replace it
                node = node.right;
            } else if (node.right == null) {
                // node has one child (left), return left child to replace it
                node = node.left;
            } else {
                // node has two children, find inorder predecessor or successor and replace node with it
                if (node.right.left == null) {
                    // right child has no left subtree, so it is the inorder successor
                    node.data = node.right.data;
                    node.right = node.right.right;
                } else {
                    // right child has a left subtree, so its inorder successor is the leftmost node in the subtree
                    TreeNode parent = node.right;
                    TreeNode successor = parent.left;
                    while (successor.left != null) {
                        parent = successor;
                        successor = successor.left;
                    }
                    node.data = successor.data;
                    parent.left = successor.right;
                }
            }
        }
        return node;
    }
    /**
     * Step 3's method display the tree
     */
    public void displayTree(TreeNode root) {
        if (root != null) {
            this.displayTree(root, circlePane.getWidth() / 2.0, this.vGap, circlePane.getWidth() / 4.0, Color.PINK);
        }
    }
    // step 3.1 display tree using recursive methods
    protected void displayTree(TreeNode root, double x, double y, double hGap, Color color) {
        if (root.right != null) {
            // add a new line
            circlePane.getChildren().add(new Line(x + hGap, y + this.vGap, x, y));
            // recursive method
            this.displayTree(root.right, x + hGap, y + this.vGap, hGap / 2.0, color);
        }
        if (root.left != null) {
            // add a new line
            circlePane.getChildren().add(new Line(x - hGap, y + this.vGap, x, y));
            // recursive method
            this.displayTree(root.left, x - hGap, y + this.vGap, hGap / 2.0, color);
        }
        // create a circle
        Circle circle = new Circle(x, y, 20);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        Text text = new Text(x - 4.0, y + 4.0, root.data + "");
        text.setFont(Font.font("Arial", FontWeight.BOLD,13));
        circlePane.getChildren().addAll(circle, text);
    }
    @FXML
    private void searchNodeCall(){
        if (nodeText.getText().equals("")){
            prompt.setText("ERROR input an integer");
        } else {
            int key = Integer.parseInt(nodeText.getText());
            boolean bool = searchNodeLoop(root, key);
            if (bool){
                prompt.setText("The node "+key+" exists");
            } else {
                prompt.setText("The node "+key+" does not exists");
            }
            nodeText.clear();
        }

    }

    public boolean searchNodeLoop(TreeNode node, int key) {
        // Base case: the node is null, so the key is not found
        if (node == null) {
            return false;
        }
        // If the key is less than the current node's data, search the left subtree
        if (key < node.data) {
            return searchNodeLoop(node.left, key);
        }
        // If the key is greater than the current node's data, search the right subtree
        else if (key > node.data) {
            return searchNodeLoop(node.right, key);
        }
        // If the key is equal to the current node's data, the key is found
        else {
            return true;
        }
    }
    @FXML
    private void loadFirstPage() throws IOException {
        HelloApplication app = new HelloApplication();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("firstPage.fxml"));
        app.getStage().getScene().setRoot(loader.load());
    }

}