package Presentation;

import static Presentation.StartscreenController.game;
import static Presentation.StartscreenController.setRoot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class HighscoreController {

    @FXML
    private TextArea highscoreText = new TextArea();

    @FXML
    public void initialize() { //loads the text from the arraylist
        String output = game.getMd().formatScore();
        highscoreText.setText(output);
    }

    @FXML
    private void handleButtonBack() throws IOException { // Goes back to the startscreen:
        setRoot("Startscreen");
    }
}
