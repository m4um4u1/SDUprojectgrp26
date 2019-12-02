package worldofzuul;

import Data.DataRaW;
import Interface.IDataRaW;
import Interface.IMetadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Metadata implements IMetadata {

    // Attributes:
    private File metaData = new File("metadata.csv");
    private IDataRaW data = new DataRaW();
    private ArrayList<String> scoreArray;
    private String playerName;
    private int score;
    private String currentRoom;
    private String output;

    // Constructor:
    public Metadata() {
        // Creates a file in the project folder if not already there:
        if (!metaData.exists()) {
            try {
                metaData.createNewFile();
            } catch (IOException ex) {
                System.out.println("Could not create a new metadata file.");
            }
        }
        scoreArray = data.readCSV();
    }
        // Updates the score:
        @Override
        public void updateScore ( int score){
            this.score += score;
        }

        // Returns the score:
        @Override
        public int getScore () {
            return score;
        }

        public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
        }

        
        public void setPlayerName(String playerName) {
        
        }
        
        @Override
        public String newUser(String playerName){
        this.playerName = playerName;
        
        for (int i = 0; i < scoreArray.size(); i += 3) {
                
                System.out.println(scoreArray);
                
                for (String s : scoreArray) {
                if (s.contains(playerName)) {
                    this.output = "Er du ikke " + playerName + "? Så log på med din bruger.";
                } else {
                    this.output = "Du opretter en ny bruger.";
                }
            
                
                score = Integer.parseInt(scoreArray.get(i + 1));
                currentRoom = scoreArray.get(i + 2); // As mentioned; not used for now, changing currentRoom in Game is not implemented.
                
                System.out.println(scoreArray);
        
            
            
        }
        }return output;
        }
        
        @Override
        public void quit (){
            data.saveCSV(scoreArray);
        }
        
        @Override
        public String formatScore() { //formats the strings in the array
            for (int i = 0; i < scoreArray.size(); i += 3) {
                
                System.out.println(scoreArray);
                
                playerName = scoreArray.get(i);
                score = Integer.parseInt(scoreArray.get(i + 1));
                currentRoom = scoreArray.get(i + 2); // As mentioned; not used for now, changing currentRoom in Game is not implemented.
                
                System.out.println(scoreArray);
                
                // Adds the username, score and room to an ArrayList:
                scoreArray.add("Player name: " + playerName); // String
                scoreArray.add("Score: " + score); // String
                scoreArray.add("Room: " + currentRoom); // String
                
                System.out.println(scoreArray);
            }

            int index = 0;
            for (String s : scoreArray) {
                output += s;
                output += "\n";
                index++;

                if (index % 3 == 0) {
                    output += "---\n";
                }
            }
            return output;
        }


    }
