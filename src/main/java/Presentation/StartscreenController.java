package Presentation;

import Data.Metadata;
import Interface.IGame;
import Interface.IMetadata;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import worldofzuul.Game;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class StartscreenController extends Application {
        public static IGame game = new Game();
        public static IMetadata md = new Metadata();
        private String name;
        private boolean isHelpOpen = false;

        @FXML
        private static Scene scene;

        @FXML
        private TextField nameTextField;

        @FXML
        private Button buttonStartGame;
        
        @FXML
        private Button grabTrashTest;

        @FXML
        private Label welcomeLabel;

        @FXML
        private Label notTheUser;
        
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
    public void handleButtonLogin() { // changes the labels to login-text with name
            name = nameTextField.getText();
            nameTextField.clear();
            welcomeLabel.setText("Hej " + name + ", klik på 'start spil' for at starte, eller 'score', for at se scoren for tidligere gennemspilninger.");
            notTheUser.setText(game.getOutput());
            buttonStartGame.setDisable(false);

    }

    @FXML
    public void handleButtonStart() { // starts the game
        game.newUser(name);

    }
    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }
    
    @FXML
    public void grabTrashTest() throws IOException{
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
            //isHelpOpen is set as open.
            this.isHelpOpen = true;
            //Sets an event that runs when the player presses on the close window button built in from Windows/Macs side.
            stageHelp.setOnCloseRequest(helpEventClose);
        }
        else {
            //Do nothing!
        }
    }
}




