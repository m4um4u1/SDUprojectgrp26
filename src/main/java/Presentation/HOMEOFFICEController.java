package Presentation;

import static Presentation.Start.game;
import static Presentation.StartscreenController.setRoot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import worldofzuul.Room;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class HOMEOFFICEController {
    private String id;
    private Room currentRoom;
    private Room nextRoom;


    @FXML
    private Button east;

    @FXML
    private ListView<?> displayInventory;
    @FXML
    private TextArea inspect;
    @FXML
    private ImageView envelope;
    @FXML
    private ImageView pringles;

   
    
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
        
        } else if (event.isSecondaryButtonDown()) {
            game.inspectTrash(id);
        }
    }
}
