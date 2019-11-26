package worldofzuul;

import Interface.IMetaData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Metadata implements IMetaData {

    // Attributes:
    private File metaData = new File("metadata.csv");

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
    }

    public void readScore() {
        // Loads the score (but not room atm) if username matches one found in the metadata.csv file:
        ArrayList<String> matcher = new ArrayList<>();

        try (
                Scanner fileReader = new Scanner(metaData);
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

        // Gets the current room's short description when the program is quit:
        public void flushData (String currentRoom){
            this.currentRoom = currentRoom;

            // Adds the username, score and room to an ArrayList:
            ArrayList<String> scoreArray = new ArrayList<>();
            scoreArray.add("Player name: " + this.playerName); // String
            scoreArray.add("Score: " + this.score); // String
            scoreArray.add("Room: " + this.currentRoom); // String

            // Adds the contents of the metadata.csv file to the ArrayList:
            try {
                Scanner fileReader = new Scanner(metaData).useDelimiter("\r\n");
                while (fileReader.hasNext()) {
                    scoreArray.add(fileReader.next());
                }
                fileReader.close();

                // Writes the ArrayList to the metadata.csv file:
                PrintWriter fileWriter = new PrintWriter(metaData);
                for (String str : scoreArray) {
                    fileWriter.println(str);
                }
                fileWriter.close();

            } catch (IOException ex) {
                System.out.println("Could not flush data to the metadata.csv file.");
            }
            // Could probably also have closed the reader and writer with "try-with-materials" -
            // - that or a "finally" block, which maybe also could save the gamestate in case of abrupt termination.
        }

        public void setPlayerName (String playerName){
            this.playerName = playerName;
        }
        
        //this will read the whole CSV and put it in a array
        public String getCSV() throws FileNotFoundException {
        ArrayList<String> scoreArray = new ArrayList<>();
        Scanner fileReader = new Scanner(metaData).useDelimiter("\r\n");
        while (fileReader.hasNext()) {
            scoreArray.add(fileReader.next());
        }
        fileReader.close();
        
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
        output.remove(output.size());
        return output;
    }
}
