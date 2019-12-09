package Presentation;

import static Presentation.StartscreenController.game;
import static Presentation.StartscreenController.setRoot;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import worldofzuul.Game;
import worldofzuul.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import worldofzuul.Trash.Trash;


public class ENTREController {
    private String id;
    private Room currentRoom;
    private Room nextRoom;
    private ArrayList<Trash> inventory;
    private ArrayList<Trash> trashInRoom;
    private Trash trash;

    @FXML
    private Button west;

    @FXML
    private Button north;


    @FXML
    private Button south;
    @FXML
    private ImageView beerCan;
    @FXML
    private ImageView pizzaBox;
    @FXML
    private ImageView pizzaSlices;

    @FXML
    private TextArea inspect;
    
    @FXML
    private ObservableList<Trash> inventoryToDisplay = FXCollections.observableArrayList();
    
    @FXML
    private ListView<Trash> displayInventory = new ListView<>(inventoryToDisplay);
    
    @FXML
    public void loadInventory() {
        inventory = game.getInventory();
        displayInventory.getItems().clear();
        
        for (int i = 0; i < inventory.size(); i++) {
            inventoryToDisplay.add(inventory.get(i));
            System.out.println(inventoryToDisplay.get(i));
            displayInventory.getItems().add(inventory.get(i));
        }
        
    }
    
    @FXML
    public void initialize() {
        checkTrash();
        loadInventory();
    }

    
    @FXML
    public void goDirection(MouseEvent event) throws IOException {
        nextRoom =  game.goRoom(event.getPickResult().getIntersectedNode().getId());
        //testroom = game.goRoom("north");

        if (nextRoom == null) {
            System.out.println(currentRoom);
        }
        else {
            nextRoom.getShortDescription();
            currentRoom = nextRoom;
            setRoot(currentRoom.getRoot());
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
            inspect.setText(game.inspectTrash(id));
        }
        
    }
    
    @FXML
    public void deposit(MouseEvent event) throws FileNotFoundException {
        trash = displayInventory.getSelectionModel().getSelectedItem();

        if (trash != null) {
//            feedback.setVisible(true);
//            feedback.setLayoutX(event.getX());
//            feedback.setLayoutY(event.getY());
//            feedback.setTranslateX(50);
//            feedback.setTranslateY(20);
//            feedback.setText(trash.getFeedback());
            game.depositTrash(trash);
            inspect.setText(trash.getFeedback());            
            loadInventory();
        }
        
        else {
            //Do nothing!
        }
    }
    
        public void checkTrash() {
        trashInRoom = game.getTrashRoom();
        
        for (int i = 0; i < trashInRoom.size(); i++) {
            System.out.println("Checking Trash!");
            if (trashInRoom.get(i).getId().equals("beerCan")) {
                beerCan.setVisible(true);
            }
            else if (trashInRoom.get(i).getId().equals("pizzaBox")) {
                pizzaBox.setVisible(true);
            }
            else if (trashInRoom.get(i).getId().equals("pizzaSlices")) {
                pizzaSlices.setVisible(true);
            }
                
        }
   }
    
}
