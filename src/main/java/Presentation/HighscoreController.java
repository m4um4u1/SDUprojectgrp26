package Presentation;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import worldofzuul.Metadata;


public class HighscoreController {


    @FXML
    private TextArea highscoreText = new TextArea();


    @FXML
    private void handleButtonBack() throws IOException { //goes back to startscreen
        StartscreenController.setRoot("Startscreen");
    }

    @FXML
    private void handleButtonLoad() { //loads the text from the arraylist, because does not work automatic until now (want it to load when opening scene)
        loadText();
    }

    //this will load the String from the metadata array to the textarea
    @FXML
    public void loadText() { //set the text from the arraylist in the textarea
        Metadata md = new Metadata();
        String output;
        output = md.getCSV();
        highscoreText.setText(output);
    }

}
