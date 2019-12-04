/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import static Presentation.StartscreenController.game;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import worldofzuul.Room;
import worldofzuul.Trash.Trash;
import worldofzuul.Trash.TrashOrganic;

public class GrabTrashExample {
    private String id;
    private Trash trash;
    private ArrayList<Trash> inventory = new ArrayList<>();
    private Room testRoom;
    
    @FXML
    private ImageView straw;
    
    @FXML
    private ImageView bananaPeel;
    
    @FXML
    private TextArea inspect;
    
    @FXML
    private Button trashcan;
    
    @FXML
    private Button nord;
    
    @FXML 
    private TextField feedback;
    
    @FXML
    private ObservableList<Trash> inventoryToDisplay = FXCollections.observableArrayList();
    
    @FXML
    private ListView<Trash> inventoryDisplay = new ListView<>(inventoryToDisplay);
    
    @FXML
    public void loadInventory() {
        inventory = game.getInventory();
        inventoryDisplay.getItems().clear();
        
        for (int i = 0; i < inventory.size(); i++) {
            inventoryToDisplay.add(inventory.get(i));
            System.out.println(inventoryToDisplay.get(i));
            inventoryDisplay.getItems().add(inventory.get(i));
        }
        
    }

    @FXML
    public void deposit(MouseEvent event) {
        //Reads the current selection and hopefully returns an object. If not there is a function for getting index, use that (extend to remove?)
        //listviewObject.getSelectionModel.getSelectionItem();
        trash = inventoryDisplay.getSelectionModel().getSelectedItem();
        System.out.println(trash);
        
        if (trash != null) {
            feedback.setVisible(true);
            feedback.setLayoutX(event.getX());
            feedback.setLayoutY(event.getY());
            feedback.setTranslateX(50);
            feedback.setTranslateY(20);
            feedback.setText(trash.getFeedback());
            game.depositTrash(trash);
            loadInventory();
        }
        
        else {
            //Do nothing!
        }
        
    }
    
        @FXML
    public void grab(MouseEvent event) {
        id = event.getPickResult().getIntersectedNode().getId();
        
        if (event.isPrimaryButtonDown()) {
            System.out.println(id);
            game.grabTrash(id);
            Node node = (Node) event.getSource();
            node.setVisible(false);
            game.printInventory();
            loadInventory();
        
        } else if (event.isSecondaryButtonDown()) {
            inspect.setWrapText(true);
            inspect.setText(game.inspectTrash(id));
        }
    }
    
    public void goNorth(MouseEvent event) {
//        game.goRoom(event.getPickResult().getIntersectedNode().getId());
        testRoom = game.goRoom(event.getPickResult().getIntersectedNode().getId());
        
        if (testRoom == null) {
            System.out.println(testRoom);
        }
        else {
            testRoom.getLongDescription();
        }
    }
}
