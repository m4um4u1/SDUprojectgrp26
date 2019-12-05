package Presentation;

import Interface.IGame;
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
import worldofzuul.Exceptions.noNameException;
import worldofzuul.Exceptions.moreStringException;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import worldofzuul.Game;

public class StartscreenController extends Application {
    
    private String name;
    private boolean isHelpOpen;
    private int clicked;
    public static final IGame game = new Game();

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
    private Button grabTrashTest;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button help;

    public static void main(String[] args) {
        launch(args);
    }

        //Sets the help window as closed when someone presses X on the window.
    EventHandler<WindowEvent> helpEventClose = new EventHandler<>() {
        @Override
        public void handle(WindowEvent we) {
            isHelpOpen = false;
            System.out.println(isHelpOpen);
        }
    };

    EventHandler<WindowEvent> gameEventClose = new EventHandler<>() {
        @Override
        public void handle(WindowEvent we) {
            try {
                game.quit();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    //GUI:
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Startscreen"), 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Sortering for Dummies");
        stage.setOnCloseRequest(gameEventClose);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartscreenController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
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
            } catch (moreStringException e) {
                exception("Brugernavnet må kun bestå af et ord.\nKlik \"OK\" for at prøve igen.");
            } catch (noNameException e) {
                exception("Du skal indtaste et brugernavn.\nKlik \"OK\" for at prøve igen.");
            }
        } else if (clicked == 1) { // Genstarter efter exception
            setRoot("Startscreen");
        }
    }

    private void exception(String ex){
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

    @FXML
    public void help() throws IOException {
        if (!isHelpOpen) {
            //First it creates a new window (scene)
            Stage stageHelp = new Stage();
            Scene sceneHelp = new Scene(loadFXML("Help"), 720, 480);
            stageHelp.show();
            stageHelp.setTitle("Hjælp");
            stageHelp.setScene(sceneHelp);
            this.isHelpOpen = true;
            //Sets an event that runs when the player presses on the close window button built in from Windows/Macs side.
            stageHelp.setOnCloseRequest(helpEventClose);
        } else {
            //Do nothing!
        }
    }

}