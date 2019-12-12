package worldofzuul;

import Data.DataRaW;
import Interface.IDataRaW;
import Interface.IMetadata;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Metadata implements IMetadata {

    public File metaData = new File("metadata.csv");
    private IDataRaW data = new DataRaW();
    private ArrayList<Player> users = new ArrayList<>();
    private String playerName;
    private Player loggedIn;
    private int score;
    private int tries;
    private String currentRoom;
    private String output;
    private String clearOutput;
    private Player player;
    private int bestScore;
    private boolean newPlayer;
    private boolean isLoggedIn;
    private int trashCountdown;

    public Metadata() {
        // Creates a file and dummy player in the project folder if not already there:
        if (!metaData.exists()) {
            try {
                metaData.createNewFile();
                metaData.setReadable(false);
                metaData.setWritable(false);
            } catch (IOException ex) {
                System.out.println("Could not create a new metadata file.");
            }
        } else {
            loadPlayers();
            Collections.sort(users); // Sorts the users according their score; highest to lowest.
        }
    }
    
    @Override
    public int getScore() {
        return score;
    }

    private void loadPlayers() { // Adds players from CSV to array
        ArrayList<String> scoreArray = data.readCSV();
        if (!scoreArray.isEmpty()) {
            for (String players : scoreArray) {
                String[] player = players.split(" ");
                String playerName = player[0];
                int score = Integer.parseInt(player[1]);
                int bestScore = Integer.parseInt(player[2]);
                String currentRoom = player[3] + " " + player[4];
                int tries = Integer.parseInt(player[5]);
                Player user = new Player(playerName, score, bestScore, currentRoom, tries);
                users.add(user);
            }
        }
    }

    @Override
    public String checkUser(String playerName) { // Checks if login is a new player, if so it gets the saved data on that player
        this.playerName = playerName;
        if (!users.isEmpty()) {
            for (Player p : users) {
                if (playerName.equals(p.getName())) {
                    output = "Er du ikke " + playerName + "? Så log på med din bruger, eller lav en ny.";
                    loggedIn = p;
                    loadPlayer();
                    newPlayer = false;
                    break;
                } else {
                    newPlayer = true;
                    output = "Du opretter en ny bruger.";
                }
            }
        } else {
            newPlayer = true;
            output = "Du opretter en ny bruger.";
        }
        isLoggedIn = true;
        return output;
    }

    public void startGame(){
        this.score = 0;
    }

    private Player addNewPlayer() { // Creates a new player
        player = new Player(this.playerName, this.score, this.bestScore, this.currentRoom, this.tries);
        return player;
    }

    private void loadPlayer() { // Loads data from current player
        this.playerName = loggedIn.getName();
        this.score = loggedIn.getScore();
        this.bestScore = loggedIn.getBestScore();
        this.currentRoom = loggedIn.getLocation();
        this.tries = loggedIn.getTries();
    }

    private void updatePlayer(){
        loggedIn.setLocation(this.currentRoom);
        loggedIn.setScore(this.score);
        loggedIn.setBestScore(this.bestScore);
        loggedIn.setTries(this.tries);
    }

    // Updates the score:
    public void updateScore(int score) { //updates score
        this.score += score;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void winConditionIncrementer() {
        trashCountdown += 1;
    }
        
    @Override
    public boolean winConditionChecker() {
        // When trash has been deposited 15 times the game ends
        if (trashCountdown == 15) {
            this.tries++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void quit() { // Saves players to csv
        if (isLoggedIn) {
            if (this.score > this.bestScore){
                this.bestScore = this.score;
            }
            if (newPlayer) {
                users.add(addNewPlayer());
            } else {
                updatePlayer();
            }
            Collections.sort(users);
            ArrayList<String> player = new ArrayList<>();
            for (Player p : users) {
                player.add(p.getName() + " " + p.getScore() + " " + p.getBestScore() + " " + p.getLocation() + " " + p.getTries());
            }
            data.saveCSV(player);
        }
    }
    public String formatScore() { // Prints players in highscore
        clearOutput = "";
        for (Player p : users) {
            clearOutput += p.toString();
        }
        return clearOutput;
    }
}
