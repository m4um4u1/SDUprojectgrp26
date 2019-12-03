package Presentation;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import worldofzuul.Exceptions.PlayernameException;

import java.io.IOException;

public class StartscreenController extends Application {

    private String name;
    private int clicked = 0;


    @FXML
    private static Scene scene;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonLogin;

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
    public void handleButtonLogin() throws IOException { // changes the labels to login-text with name
        name = nameTextField.getText();
        if (clicked == 0) {
            try {
                if (name.contains(" ") || name.isEmpty()) {
                    throw new PlayernameException("Wrong formation of username");
                }
                notTheUser.setText(Start.game.getMd().checkUser(name));
                welcomeLabel.setText("Hej " + name + ", klik på 'start spil' for at starte, eller 'score', for at se scoren for tidligere gennemspilninger.");
                buttonStartGame.setDisable(false); //sets StartGame-button visible if logged in

            } catch (PlayernameException e) {
                notTheUser.setTextFill(Color.RED);
                notTheUser.setText("Du skal indtaste et brugernavn og det må kun bestå af et ord"); //resets label if someone was logged in before
                welcomeLabel.setText(""); //resets welcomeLabel when exception happends
                buttonLogin.setText("OK");
                clicked++;
            }
        } else if (clicked == 1) { //restarts after exception
                setRoot("Startscreen");
        }
    }

    @FXML
    public void handleButtonStart() throws IOException { // starts the game
        setRoot("debug"); //can be changed to the real gamestart FXML
    }

    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }
}




