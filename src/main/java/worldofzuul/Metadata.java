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
    private int id;
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
                users.add(new Player(0, "Maurice", 130, "i indkørslen"));
            } catch (IOException ex) {
                System.out.println("Could not create a new metadata file.");
            }
        } else {
            loadPlayers();
        }
    }

    public void loadPlayers(){ // adds players from CSV to array
        ArrayList<String> scoreArray = data.readCSV();
        for(String players : scoreArray){
            String[] player = players.split(" ");
            int id = Integer.parseInt(player[0]);
            String playerName = player[1];
            int score = Integer.parseInt(player[2]);
            String currentRoom = player[3];
            System.out.println(id + playerName + score + currentRoom);
            Player user = new Player(id, playerName, score, currentRoom);
            users.add(user);
        }
    }

    // Updates the score:
    @Override
    public void updateScore(int score) { //upsates score
        this.score += score;
    }

    private void loadPlayer(){ //loads data from current player
        this.playerName = users.get(chooser).getName();
        this.score = users.get(chooser).getScore();
        this.currentRoom = users.get(chooser).getLocation();
    }

    private void savePlayer(){ //updates player score and location in array
        users.get(chooser).setScore(this.score);
        users.get(chooser).setLocation(this.currentRoom);
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public String checkUser(String playerName) { //checks if new player and gets the current player
        for (Player p : users) {
            if (playerName.equals(p.getName())) {
                chooser = p.getId();
                output = "Er du ikke " + playerName + "? Så log på med din bruger, eller lav en ny.";
                loadPlayer();
                break;
            } else {
                newPlayer = true;
                this.playerName = playerName;
                output = "Du opretter en ny bruger.";
            }
        }
        return output;
    }

    private Player addNewPlayer(){ //ceates a new player
        System.out.println(users.size());
        int id = users.size();
        player = new Player(id, this.playerName, this.score, this.currentRoom);
        return player;
    }

    @Override
    public void quit() { // saves players to scv
        if (newPlayer) {
            users.add(addNewPlayer());
        } else {
            savePlayer();
        }
            ArrayList<String> player = new ArrayList<>();
            for (Player p : users) {
                System.out.print(p.getId() + " " + p.getName() + " " + p.getScore() + " " + p.getLocation());
                player.add(p.getId() + " " + p.getName() + " " + p.getScore() + " " + p.getLocation());
            }
            data.saveCSV(player);
        }

    @Override
    public String formatScore() { // prints players in highscore
        for (Player p : users){
            outp+= p.toString();
        }
        return outp;
    }
}
