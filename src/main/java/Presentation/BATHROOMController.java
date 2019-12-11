package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import worldofzuul.Room;
import worldofzuul.Trash.Trash;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static Presentation.StartscreenController.*;

public class BATHROOMController {

    private String id;
    private Room currentRoom;
    private Room nextRoom;
    private ArrayList<Trash> inventory;
    private ArrayList<Trash> trashInRoom;
    private Trash trash;
    private boolean isHelpOpen;
    private boolean isCorrect;
    private ObservableList<Trash> inventoryToDisplay = FXCollections.observableArrayList();

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView toothbrush;

    @FXML
    private TextArea inspect;

    @FXML
    private ImageView toiletpaperroll;

    @FXML
    private ListView<Trash> displayInventory = new ListView<>(inventoryToDisplay);

    @FXML
    private Button trashbin;

    public void loadInventory() {
        inventory = game.getInventory();
        displayInventory.getItems().clear();

        for (int i = 0; i < inventory.size(); i++) {
            inventoryToDisplay.add(inventory.get(i));
            System.out.println(inventoryToDisplay.get(i));
            displayInventory.getItems().add(inventory.get(i));
        }

    }

    public void initialize() {
        scoreLabel.setText("Score: " + (String.valueOf(game.getMd().getScore())));
        checkTrash();
        loadInventory();
        inspect.setStyle("-focus-color: transparent; -fx-text-box-border: transparent;");

    }

    @FXML
    public void goDirection(MouseEvent event) throws IOException {
        nextRoom = game.goRoom(event.getPickResult().getIntersectedNode().getId());
        //testroom = game.goRoom("north");

        if (nextRoom == null) {
            System.out.println(currentRoom);
        } else {
            nextRoom.getShortDescription();
            currentRoom = nextRoom;
            setRoot(currentRoom.getRoot());
        }
    }

    //example
    @FXML
    public void grab(MouseEvent event) {
        id = event.getPickResult().getIntersectedNode().getId();
        if (event.isPrimaryButtonDown() && inventory.size() < 4) {
            System.out.println(id);
            game.grabTrash(id);
            Node node = (Node) event.getSource();
            node.setVisible(false);
            //game.printInventory();
            loadInventory();

        } else if (event.isSecondaryButtonDown()) {
            inspect.setText(game.inspectTrash(id));
        }else{
            inspect.setText("Din ryksæk er fyldt!");
        }
    }
        @FXML
        public void inspectInventory(MouseEvent event) {
        if (event.isSecondaryButtonDown()) {
            if (displayInventory.getSelectionModel().getSelectedItem() != null) {
                trash = displayInventory.getSelectionModel().getSelectedItem();
                inspect.setText(trash.getDescription());
            }
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
            isCorrect = game.depositTrash(trash);

            if (isCorrect) {
                inspect.setText("Sådan min ven! Det er korrekt.");
            } else {
                inspect.setText(trash.getFeedback());
            }
            loadInventory();
            trash = null;
            scoreLabel.setText("Score: " + (String.valueOf(game.getMd().getScore())));
        } else {
            //Do nothing!
        }

    }

    public void checkTrash() {
        trashInRoom = game.getTrashRoom();

        for (int i = 0; i < trashInRoom.size(); i++) {
            if (trashInRoom.get(i).getId().equals("toothbrush")) {
                toothbrush.setVisible(true);
            } else if (trashInRoom.get(i).getId().equals("toiletpaperroll")) {
                toiletpaperroll.setVisible(true);
            }
        }
    }

    @FXML
    public void help() throws IOException {
        if (!isHelpOpen) {
            //First it creates a new window (scene)
            Stage stageHelp = new Stage();
            Scene sceneHelp = new Scene(loadFXML("Help"), 720, 480);
            stageHelp.show();
            stageHelp.setTitle("Hjælp");
            stageHelp.setScene(sceneHelp);
            this.isHelpOpen = true;
            //Sets an event that runs when the player presses on the close window button built in from Windows/Macs side.
            stageHelp.setOnCloseRequest(helpEventClose);
        } else {
            //Do nothing!
        }
    }

    //Sets the help window as closed when someone presses X on the window.
    EventHandler<WindowEvent> helpEventClose = new EventHandler<>() {
        @Override
        public void handle(WindowEvent we) {
            isHelpOpen = false;
            System.out.println(isHelpOpen);
        }
    };

}
