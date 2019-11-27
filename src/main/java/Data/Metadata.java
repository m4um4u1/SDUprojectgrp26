package Data;

import Interface.IMetadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Metadata implements IMetadata {

    // Attributes:
    private File metaData = new File("metadata.csv");
    private ArrayList<String> scoreArray = new ArrayList<>();
    private String playerName;  // Prompted at start
    private int score;          // Manipulated during
    private String currentRoom; // Acquired when quit

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
        readCSV();
    }

    public void readScore() {
        // Loads the score (but not room atm) if username matches one found in the metadata.csv file:
        ArrayList<String> matcher = new ArrayList<>();

        try (
                Scanner fileReader = new Scanner(metaData)
        ) {
            while (fileReader.hasNext()) {
                matcher.add(fileReader.next());
            }
            fileReader.close();

            int i = 0;
            for (String srt : matcher) {
                i++;
                if (srt.contains(playerName)) {
                    this.score = Integer.parseInt(matcher.get(i + 1));
                    this.currentRoom = matcher.get(i + 2); // As mentioned; not used for now, changing currentRoom in Game is not implemented.
                    System.out.println("Gemt brugernavn fundet; indlæser tidliger score på: " + getScore());
                    break;
                }
            }


        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file.");
        }

    }
        // Updates the score:
        public void updateScore (int score){
            this.score += score;
        }

        // Returns the score:
        public int getScore() {
            return score;
        }

        public String newUser(String playerName) {
            String output = null;
            for (String s : scoreArray) {
                if (s.contains(playerName)) {
                    output = "Er du ikke "+playerName+"? Så log på med din bruger.";
                } else {
                    output = "Du opretter en ny bruger.";
                }
            }
            return output;
        }
        // Gets the current room's short description when the program is quit:
        public void flushData (String currentRoom) throws FileNotFoundException {
            this.currentRoom = currentRoom;

            // Adds the username, score and room to an ArrayList:
            scoreArray.add("Player name: " + this.playerName); // String
            scoreArray.add("Score: " + this.score); // String
            scoreArray.add("Room: " + this.currentRoom); // String

            // Writes the ArrayList to the metadata.csv file:
            PrintWriter fileWriter = new PrintWriter(metaData);
            for (String str : scoreArray) {
                fileWriter.println(str);
            }
            fileWriter.close();

        }

        public void setPlayerName (String playerName){
            this.playerName = playerName;
        }

        public void readCSV() {
            try {
                Scanner fileReader = new Scanner(metaData).useDelimiter("\r\n");
                while (fileReader.hasNext()) {
                    scoreArray.add(fileReader.next());
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
            //this will read the whole CSV and put it in a array
        public String getCSV(){
        String output = "";
        int index = 0;
        
        for(String s: scoreArray){
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
