package worldofzuul;

import Data.DataRaW;
import Interface.IDataRaW;
import Interface.IMetadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Metadata implements IMetadata {

    // Attributes:
    public File metaData = new File("metadata.csv");
    private IDataRaW data = new DataRaW();

    private ArrayList<Player> users = new ArrayList<>();
    private int chooser;
    private String playerName;
    private int score = 0;
    private String currentRoom;
    private String output;
    private String outp;
    private Player player;
    private boolean newPlayer = false;

    // Constructor:
    public Metadata() {
        // Creates a file and dummy player in the project folder if not already there:
        if (!metaData.exists()) {
            try {
                metaData.createNewFile();
                loadPlayer();
                quit();
            } catch (IOException ex) {
                System.out.println("Could not create a new metadata file.");
            }
        } else {
            loadPlayers();
        }
    }

    public void loadPlayers(){ // adds players from CSV to array
        ArrayList<String> scoreArray = data.readCSV();
        if (!scoreArray.isEmpty()) {
            for(String players : scoreArray){
            String[] player = players.split(" ");
            String playerName = player[0];
            int score = Integer.parseInt(player[1]);
            String currentRoom = player[2] + " " + player[3];
            System.out.println(playerName + score + currentRoom);
            Player user = new Player(playerName, score, currentRoom);
            users.add(user);
            }
        }
    }

    // Updates the score:
    @Override
    public void updateScore(int score) { //updates score
        this.score += score;
    }

    private void loadPlayer(){ //loads data from current player
        this.playerName = users.get(chooser).getName();
        this.score = users.get(chooser).getScore();
        this.currentRoom = users.get(chooser).getLocation();
    }

    private void updatePlayer(){ //updates player score and location in array
        users.get(chooser).setScore(this.score);
        users.get(chooser).setLocation(this.currentRoom);
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    @Override
    public String checkUser(String playerName) { //checks if new player and gets the current player
        this.playerName = playerName;
        if (!users.isEmpty()) {
            for (Player p : users) {
                if (playerName.equals(p.getName())) {
                    chooser = users.indexOf(p);
                    output = "Er du ikke " + playerName + "? Så log på med din bruger, eller lav en ny.";
                    loadPlayer();
                    users.remove(p);
                }
                break;
            }
        } else {
            newPlayer = true;
            output = "Du opretter en ny bruger.";
        }
        return output;
    }

    private Player addNewPlayer(){ //creates a new player
        player = new Player(this.playerName, this.score, this.currentRoom);
        return player;
    }

    private void resetData() {
        this.playerName = "";
        this.score = 0;
        this.currentRoom = "";
    }
    
    @Override
    public void quit() { // saves players to scv
        if (newPlayer || users.isEmpty()) {
            users.add(addNewPlayer());
        } else {
            updatePlayer();
        }
            ArrayList<String> player = new ArrayList<>();
            for (Player p : users) {
                System.out.print(p.getName() + " " + p.getScore() + " " + p.getLocation());
                player.add(p.getName() + " " + p.getScore() + " " + p.getLocation());
            }
            data.saveCSV(player);
            resetData();
        }

    @Override
    public String formatScore() { // prints players in highscore
        outp = ""; // Clears the highscore list
        for (Player p : users){
            outp+= p.toString();
        }
        return outp;
    }
}
