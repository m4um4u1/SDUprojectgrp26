package Presentation;

import Interface.IMetadata;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;

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
        String output = Start.md.formatScore();
        highscoreText.setText(output);
    }

}
