package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import Trash.*;

public class Room 
{
    
    //Adding arraylist of trash in rooms
    private String description;
    private HashMap<String, Room> exits;
    ArrayList<Trash> trash = new ArrayList<Trash>();

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    //Added: Showing what trash is in the room when entering
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\nTrash in the room: \n";
        returnString += getRoomTrash();
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    //Get trash from the room - the index 
    public Trash getTrash (int index) {
        return trash.get(index);
    }
    
    //Method to grab the trash IF there is any and it matches the input 
    public Trash getTrash (String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getDescription().equals(trashName)) {
                return trash.get(i);
            }
            
        }
        return null;
    }
    
    //Set a particular piece of trash in the room. 
    public void setTrash (Trash newTrash) {
        trash.add(newTrash);
    }
    
    //Description of the trash in the rooms 
    public String getRoomTrash() {
        String output = "";
        for (int i = 0; i < trash.size(); i++) {
            output += trash.get(i).getDescription() + " ";
        }
        return output;
    }
    
    //Method to remove the trash from the room after grabbing it. 
    //Eventuelt lav et for each loop
    public void removeTrash (String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getDescription().equals(trashName)) {
                trash.remove(i);
            }
        }
    }
        
}

