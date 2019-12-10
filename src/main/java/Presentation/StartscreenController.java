package Presentation;

import Interface.IGame;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Exceptions.noNameException;
import Exceptions.moreStringException;

import java.io.IOException;

import javafx.stage.WindowEvent;
import worldofzuul.Game;

public class StartscreenController extends Application {

    public static final IGame game = new Game();

    private String name;
    private int clicked;

    @FXML
    private static Scene scene;

    @FXML
    private Button buttonLogin;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label notTheUser;

    @FXML
    private Button buttonStartGame;

    @FXML
    private TextField nameTextField;

    //GUI:
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Startscreen"), 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Sortering for Dummies");
        stage.setOnCloseRequest(closeGameWindow);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartscreenController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void load(String[] args) {
        launch(args);
    }

    // Buttons:
    @FXML
    public void handleButtonLogin() throws IOException { // changes the labels to login-text with name
        name = nameTextField.getText();
        if (clicked == 0) {
            try {
                if (name.contains(" ")) {
                    throw new moreStringException("Name can only be one word");
                } else if (name.isEmpty()) {
                    throw new noNameException("");
                }
                notTheUser.setText(game.getMd().checkUser(name));
                welcomeLabel.setText("Hej " + name + ", klik på 'start spil' for at starte, eller 'score', for at se scoren for tidligere gennemspilninger.");
                buttonStartGame.setDisable(false); //sets StartGame-button visible if logged in
                nameTextField.clear();
            } catch (moreStringException e) {
                exception("Brugernavnet må kun bestå af et ord.\nKlik \"OK\" for at prøve igen.");
            } catch (noNameException e) {
                exception("Du skal indtaste et brugernavn.\nKlik \"OK\" for at prøve igen.");
            }
        } else if (clicked == 1) { // Genstarter efter exception
            setRoot("Startscreen");
        }
    }

    private void exception(String ex) {
        notTheUser.setText(ex);
        notTheUser.setTextFill(Color.RED);
        welcomeLabel.setText(""); // Resets welcomeLabel when exception happens
        buttonLogin.setText("OK");
        nameTextField.setDisable(true); // Disables textfield so user does not type in a working username before clicking OK
        clicked++;
    }

    @FXML
    public void handleButtonStart() throws IOException { // starts the game
        setRoot("DRIVEWAY");
    }

    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }

    EventHandler<WindowEvent> closeGameWindow = new EventHandler<>() {
        @Override
        public void handle(WindowEvent we) {
            try {
                // Gets the metadata object from Game and calls its quit method:
                game.getMd().quit();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }
        }
    };
}

