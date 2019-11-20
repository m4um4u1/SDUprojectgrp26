package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HighscoreController {

    @FXML
    private TextArea highscoreText = new TextArea();

    private List<String> score = new ArrayList<>();

    public HighscoreController() throws FileNotFoundException { //starts the readCSV method
        readCSV();
    }

    @FXML
    private void handleButtonBack() throws IOException { //goes back to startscreen
        StartscreenController.setRoot("Startscreen");
    }

    @FXML
    private void handleButtonLoad() { //loads the text from the arraylist, because does not work automatic until now (want it to load when opening scene)
        loadText();
    }

    public void readCSV() throws FileNotFoundException { //reads from the metadata.csv to the arraylist
        File metaData = new File("metadata.csv");
        Scanner scanner = new Scanner(metaData).useDelimiter("\r\n");
        while (scanner.hasNext()) {
            score.add(scanner.next());
        }
        scanner.close();
    }
    @FXML
    public void loadText(){ //set the text from the arraylist in the textarea
        String output = "";
        for (String s : score) {
            output += s;
        }
        highscoreText.setText(output);
    }
}
