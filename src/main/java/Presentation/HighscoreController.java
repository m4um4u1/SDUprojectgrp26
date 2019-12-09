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

    @FXML
    private void handleButtonBack() throws IOException { // Goes back to the startscreen:
        setRoot("Startscreen");
    }

    @FXML
    private void handleButtonLoad() { //loads the text from the arraylist, because does not work automatic until now (want it to load when opening scene)
        loadText();
        buttonLoad.setDisable(true);
    }

    // Loads the String from the metadata array to the textarea:
    @FXML
    public void loadText() { //set the text from the arraylist in the textarea
        String output = game.getMd().formatScore();
        highscoreText.setText(output);
        
    }

}
