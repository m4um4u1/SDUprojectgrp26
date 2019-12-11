package worldofzuul;

import static Presentation.StartscreenController.game;
import Data.DataRaW;
import Interface.IDataRaW;
import Interface.IMetadata;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Metadata implements IMetadata {

    // Attributes:
    public File metaData = new File("metadata.csv");
    private IDataRaW data = new DataRaW();

    private ArrayList<Player> users = new ArrayList<>();
    private int chooser;
    private String playerName;
    private int score;
    private int tries;
    private String currentRoom;
    private String output;
    private String outp;
    private Player player;
    private boolean newPlayer;
    private int trashCountdown;

    // Constructor:
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
        }
    }

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
                String currentRoom = player[2] + " " + player[3];
                int tries = Integer.parseInt(player[4]);
                Player user = new Player(playerName, score, currentRoom, tries);
                users.add(user);
            }
        }
    }

    @Override
    public String checkUser(String playerName) { // Checks if new player and gets the current player
        this.playerName = playerName;
        if (!users.isEmpty()) {
            for (Player p : users) {
                if (playerName.equals(p.getName())) {
                    chooser = users.indexOf(p);
                    output = "Er du ikke " + playerName + "? Så log på med din bruger, eller lav en ny.";
                    loadPlayer();
                    users.remove(p);
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
        return output;
    }

    private Player addNewPlayer() { // Creates a new player
        player = new Player(this.playerName, this.score, this.currentRoom, this.tries);
        return player;
    }

    private void loadPlayer() { // Loads data from current player
        this.playerName = users.get(chooser).getName();
        this.currentRoom = users.get(chooser).getLocation();
        this.tries = users.get(chooser).getTries();
    }

    // Updates the score:
    public void updateScore(int score) { //updates score
        this.score += score;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

   /* private void resetData() {
        this.playerName = "";
        this.score = 0;
        this.currentRoom = "";
        this.tries = 0;
    }*/

    // It was first attempted as a size check of inventory + room trash, but each room had its size, and not game-wide as needed.
    public boolean winCondition() throws FileNotFoundException {
        trashCountdown += 1;
        // When trash has been deposited 15 times (the hardcoded number of trash in the game, the condition is met and the game starts to end
        if (trashCountdown == 15) {
            this.tries++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void quit() { // Saves players to csv
        users.add(addNewPlayer());
        ArrayList<String> player = new ArrayList<>();
        for (Player p : users) {
            player.add(p.getName() + " " + p.getScore() + " " + p.getLocation() + " " + p.getTries());
        }
        data.saveCSV(player);
       // resetData();
        System.out.println("GAME ENDS/QUITS HERE");
    }

    public String formatScore() { // Prints players in highscore
        outp = ""; // Clears the highscore list
        Collections.sort(users); // Sorts the users according their score; highest to lowest.
        for (Player p : users) {
            outp += p.toString();
        }
        return outp;
    }
}
