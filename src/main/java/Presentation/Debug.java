package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.FileNotFoundException;

public class Debug {

        @FXML
        private Button buttonScore;

        @FXML
        private Button buttonAfslut;


    @FXML
   public void buttonHandleScore(){
        Start.md.updateScore(10);
   }
   @FXML
    public void buttonHandleQuit() throws FileNotFoundException {
       Start.md.quit();
   }
}

