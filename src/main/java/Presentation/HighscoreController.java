package Presentation;

import static Presentation.StartscreenController.game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.io.IOException;
import static Presentation.StartscreenController.setRoot;

public class HighscoreController {
    @FXML
    private Button buttonLoad;
    @FXML
    private TextArea highscoreText = new TextArea();
    
    // Loads the String from the metadata array to the textarea:
    @FXML
    public void initialize() { //set the text from the arraylist in the textarea
        String output = game.getMd().formatScore();
        highscoreText.setText(output);
    }

    @FXML
    private void handleButtonBack() throws IOException { // Goes back to the startscreen:
        setRoot("Startscreen");
    }

}
