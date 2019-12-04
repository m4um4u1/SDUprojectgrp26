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
import worldofzuul.Game;
import worldofzuul.Exceptions.moreStringException;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class StartscreenController extends Application {
    public static IGame game = new Game();
    private String name;
    private boolean isHelpOpen = false;
    private int clicked = 0;

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

    //Sets the help window as closed when someone presses X on the window.
    EventHandler<WindowEvent> helpEventClose = new EventHandler<>() {
        @Override
        public void handle(WindowEvent we) {
            isHelpOpen = false;
            System.out.println(isHelpOpen);
        }
    };

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
        launch(args);
    }

    //Buttons:

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
                notTheUser.setText(Start.game.getMd().checkUser(name));
                welcomeLabel.setText("Hej " + name + ", klik på 'start spil' for at starte, eller 'score', for at se scoren for tidligere gennemspilninger.");
                buttonStartGame.setDisable(false); //sets StartGame-button visible if logged in
            } catch (moreStringException e) {
                exception("Brugernavnet må kun bestå af et ord.\nKlik \"OK\" for at prøve igen.");
            } catch (noNameException e) {
                exception("Du skal indtaste et brugernavn.\nKlik \"OK\" for at prøve igen.");
            }
        } else if (clicked == 1) { //genstarter efter exception
            setRoot("Startscreen");
        }
    }

    private void exception(String ex){
        notTheUser.setText(ex);
        notTheUser.setTextFill(Color.RED);
        welcomeLabel.setText(""); //resets welcomeLabel when exception happends
        buttonLogin.setText("OK");
        nameTextField.setDisable(true); //sets textfield to disabled so you dont get confused
        clicked++;
    }
    @FXML
    public void handleButtonStart() throws IOException { // starts the game
        setRoot("debug");
    }

    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }

    @FXML
    public void grabTrashTest() throws IOException {
        setRoot("GrabTrashTest");
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




