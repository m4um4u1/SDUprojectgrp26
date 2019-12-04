package Presentation;

import static Presentation.StartscreenController.game;
import static Presentation.StartscreenController.setRoot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import worldofzuul.Game;
import worldofzuul.Room;

import java.io.IOException;
import java.util.Set;


public class ENTREController {
    private String id;
    private Room currentRoom;
    private Room nextRoom;

    @FXML
    private Button west;

    @FXML
    private Button north;

    @FXML
    private Button east;

    @FXML
    private Button south;

    
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
    
    
}
