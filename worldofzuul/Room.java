package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import Trash.*;
import TrashBin.*;

public class Room 
{
    
    //Adding arraylist of trash in rooms
    private String description;
    private HashMap<String, Room> exits;
    private TrashBin trashbin;
    ArrayList<Trash> trash = new ArrayList<Trash>();

    //Used if there isn't any trash or trashbin(s) in the room
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }
    
    //Used if there is a trashbin but no trash
    public Room(String description, TrashBin trashbin) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.trashbin = trashbin;
    }
    
    //Used if there is trash but no trashbin
    public Room(String description, ArrayList<Trash> trash) {
       this.description = description;
       exits = new HashMap<String, Room>();
       
        //Saves all elements from input ArrayList into object ArrayList
//        for (int i = 0; i < trash.size(); i++) {
//            this.trash.add(trash.get(i));
//        }
        // MIGHT WANT TO DELETE THIS CLUTTER, HAS NO USE SO FAR
    }
    
    //Used if there is trash and a trashbin
    public Room(String description, TrashBin trashbin, ArrayList<Trash> trash) {
       this.description = description;
       exits = new HashMap<String, Room>();
       this.trashbin = trashbin;
       
       //Saves all elements from input ArrayList into object ArrayList
       for (int i = 0; i < trash.size(); i++) {
           this.trash.add(trash.get(i));
       }
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
        return "Du er " + description + ".\n" + getExitString();
    }

    //Added: Showing what trash is in the room when entering
    private String getExitString()
    {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        returnString += "\n" + "\n" + "Affald i rummet: \n";
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
            if (trash.get(i).getName().equals(trashName)) {
                return trash.get(i);
            }
            
        }
        return null;
    }
    
    //Adds trash to the ArrayList trash    
    public Room addTrash (Trash trash) {
        this.trash.add(trash);
        return this;
    }
    
    //Description of the trash in the rooms 
    public String getRoomTrash() {
        String output = "";
        for (int i = 0; i < trash.size(); i++) {
            output += trash.get(i).getName() + " ";
        }
        return output;
    }
    
    //Method to remove the trash from the room after grabbing it. 
    //Eventuelt lav et for each loop
    public void removeTrash (String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(trashName)) {
                trash.remove(i);
            }
        }
    }

    public TrashBin getTrashbin() {
        return trashbin;
    }
        
}

