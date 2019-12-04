package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

import worldofzuul.Trash.*;
import worldofzuul.Trashbin.*;

public class Room {

    //Adding arraylist of trash in rooms
    private String description;
    private HashMap<String, Room> exits;
    private Trashbin trashbin;
    ArrayList<Trash> trash = new ArrayList<Trash>();

    //Used if there is a trashbin but no trash
    public Room(String description, Trashbin trashbin) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.trashbin = trashbin;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }


    public String getLongDescription() {
        return "Du er " + description + ".\n" + getExitString();
    }

    //Added: Showing what trash is in the room when entering
    private String getExitString() {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\n" + "\n" + "Affald i rummet: \n";
        returnString += getRoomTrash();
        return returnString;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    //Method to grab the trash IF there is any and it matches the input 
    public Trash getTrash(String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(trashName)) {
                return trash.get(i);
            }
        }
        return null;
    }

    //Adds trash to the ArrayList trash    
    public Room addTrash(Trash trash) {
        this.trash.add(trash);
        return this;
    }


    //Method to remove the trash from the room after grabbing it. 
    //Eventuelt lav et for each loop
    public void removeTrash(String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(trashName)) {
                trash.remove(i);
            }
        }
    }
    //Name of the trash in the rooms
    public ArrayList<Trash> getRoomTrash() {
        return this.trash;
    }
    public Trashbin getTrashbin() {
        return trashbin;
    }

    //en Metode til at giver en beskrivelse af TrashBin:
    public String getTrashbinDescription() {
        return trashbin.getDescription();
    }

    @Override
    public String toString() {
        return description;
    }
}
