package Presentation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import worldofzuul.Trash.Trash;
import worldofzuul.Trash.TrashOrganic;

public class GrabTrashExample {
    private String id;
    private Trash trash;
    
    @FXML
    private ImageView straw;
    
    @FXML
    private ImageView bananaPeel;
    
    @FXML
    private TextArea inspect;
    
    @FXML
    private Button trashcan;
    
    @FXML 
    private TextField feedback;
    
    @FXML
    public void grab(MouseEvent event) {
        id = event.getPickResult().getIntersectedNode().getId();
        
        if (event.isPrimaryButtonDown()) {
            System.out.println(id);
            Start.game.grabTrash(id);
            Node node = (Node) event.getSource();
            node.setVisible(false);
            Start.game.printInventory();
        
        } else if (event.isSecondaryButtonDown()) {
            inspect.setWrapText(true);
            inspect.setText(Start.game.inspectTrash(id));
        }
    }

    @FXML
    public void deposit(MouseEvent event) {
        //Reads the current selection and hopefully returns an object. If not there is a function for getting index, use that (extend to remove?)
        //listviewObject.getSelectionModel.getSelectionItem();
        
//        if (listViewObject.getSelectionModel.getSelectionItem() != null) {
            this.trash = new TrashOrganic("bananaPeel", "bananskræl", "Meget brun, pas på du ikke falder.", "Det skal i madaffaldsspanden fordi det er en madrest.");
            feedback.setVisible(true);
            feedback.setLayoutX(event.getX());
            feedback.setLayoutY(event.getY());
            feedback.setTranslateX(50);
            feedback.setTranslateY(20);
            feedback.setText(trash.getFeedback());
            Start.game.depositTrash(this.trash);
//        }
        
//        else {
//            //Print: Nothing selected!
//        }
        
    }
}
