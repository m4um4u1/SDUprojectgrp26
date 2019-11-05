package worldofzuul;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Metadata {
    
    // Attributes:
    private File metaData = new File ("metadata.csv");
    private ArrayList<Object> scoreArray = new ArrayList<>();
    
    private String playerName;  // Prompted at start
    private int score;          // Manipulated during
    private String currentRoom; // Acquired when quit

    // Constructor:
    public Metadata() {
        
        // Creates a file in the project folder if not already existant:
        if (!metaData.exists()) {
            try {
                metaData.createNewFile();
            } catch (IOException ex) {
                System.out.println("Could not create a new metadata file.");
            }
        }
        
        // Prompts the player for a name:
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a playername:");
        System.out.print("> ");
        playerName = userInput.nextLine();
    }
    
    // Methods:
    
    // Updates the score:
    public void updateScore(int score) {
        this.score += score;
    }
    // Gets the current score:
    public int getScore() {
        return score;
    }
    
    // Gets the current room (short description, when the program is quit):
    public void flushData(String currentRoom) {
        this.currentRoom = currentRoom;
        
        // Adds the name, score and room to the ArrayList:
        scoreArray.add("Player name: " + playerName); // String
        scoreArray.add("Score: " + score); // String
        scoreArray.add("Room: " + currentRoom); // String
        
        try (
//            Scanner reader = new Scanner(this.metaData);
            PrintWriter output = new PrintWriter(metaData);
            )   {
            
//            while(reader.hasNext()); {
//                scoreArray.add(reader.next());
//            }
            
            // Writes the ArrayList to the file:
            for (Object obj : scoreArray) {
                output.print(obj);
            }
            
        } catch (IOException ex) {
            System.out.println("Could not flush data to the metadata file.");
        }
    }
}