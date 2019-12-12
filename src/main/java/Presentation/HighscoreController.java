package Presentation;

import static Presentation.StartscreenController.game;
import static Presentation.StartscreenController.setRoot;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;
import javafx.scene.control.Button;

public class HighscoreController {

    @FXML
    private TextArea highscoreText = new TextArea();
    @FXML
    private Button buttonBack;

    public void initialize() throws FileNotFoundException { //loads the text from the arraylist
        String output = game.getMd().formatScore();
        highscoreText.setText(output);
        
        if (game.getMd().winConditionChecker()) {
            buttonBack.setText("Afslut");
        }
    }

    @FXML
    private void handleButtonBack() throws IOException { // Goes back to the startscreen:
        if (!game.getMd().winConditionChecker()) {
            setRoot("Startscreen");
        } else {
            System.exit(0);
        }
    }
}
