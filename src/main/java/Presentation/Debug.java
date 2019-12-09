package Presentation;

import static Presentation.StartscreenController.game;
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
        game.getMd().updateScore(10);
    }

    @FXML
    public void buttonHandleQuit() throws IOException {
        game.quit();
        setRoot("Startscreen");
    }
}

