/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation;

import Interface.IMetaData;
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
import worldofzuul.Metadata;

import java.io.IOException;

public class StartscreenController extends Application {


        private String name;
        private IMetaData md = new Metadata();

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
            notTheUser.setText(md.newUser(name));
            buttonStartGame.setDisable(false);
            Game game = new Game();
    }

    @FXML
    public void handleButtonStart() { // starts the game

        md.setPlayerName(name);
        md.readScore();

    }
    @FXML
    public void handleButtonScore() throws IOException { //changes scene to highscore
        setRoot("Highscore");
    }

}




