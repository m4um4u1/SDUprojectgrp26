package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static Presentation.StartscreenController.setRoot;

public class Debug {

    @FXML
    private Button buttonScore;

    @FXML
    private Button buttonAfslut;

    @FXML
    public void buttonHandleScore() {
        Start.game.getMd().updateScore(10);
    }

    @FXML
    public void buttonHandleQuit() throws IOException {
        Start.game.quit();
        setRoot("Startscreen");
    }
}

