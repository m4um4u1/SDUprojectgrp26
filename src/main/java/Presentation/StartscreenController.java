package Presentation;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class StartscreenController extends Application {

        private String name;

        @FXML
        private static Scene scene;

        @FXML
        private TextField nameTextField;

        @FXML
        private Button buttonStartGame;

        @FXML
        private Label welcomeLabel;

        @FXML
        private Label notTheUser;

//GUI:
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Startscreen"), 720, 480);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sortering for Dummies");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartscreenController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void load(String[] args) {
        launch();
    }

    //Buttons:

    @FXML
    public void handleButtonLogin() { // changes the labels to login-text with name
            name = nameTextField.getText();
            nameTextField.clear();
            welcomeLabel.setText("Hej " + name + ", klik på 'start spil' for at starte, eller 'score', for at se scoren for tidligere gennemspilninger.");
            notTheUser.setText(Start.game.getMd().checkUser(name));
            buttonStartGame.setDisable(false);
    }

    @FXML
    public void handleButtonStart() throws IOException { // starts the game
        setRoot("debug");
    }
    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }
}




