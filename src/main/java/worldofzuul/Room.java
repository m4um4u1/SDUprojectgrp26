package worldofzuul;

import java.util.ArrayList;
import java.util.HashMap;
import worldofzuul.Trash.Trash;
import worldofzuul.Trashbin.Trashbin;

public class Room {

    public HashMap<String, Room> exits;
    private ArrayList<Trash> trash = new ArrayList<>();
    private Trashbin trashbin;
    private String root;
    private String description;

    // Used if there is a trashbin
    public Room(String description, Trashbin trashbin, String root) {
        exits = new HashMap<String, Room>();
        this.trashbin = trashbin;
        this.root = root;
        this.description = description;
    }

    public Room(String description, String root) {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    // Method to grab the trash IF there is any and it matches the input 
    public Trash getTrash(String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(trashName)) {
                return trash.get(i);
            }
        }
        return null;
    }

    public Room addTrash(Trash trash) {
        this.trash.add(trash);
        return this;
    }

    public void removeTrash(String trashName) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(trashName)) {
                trash.remove(i);
            }
        }
    }

    public ArrayList<Trash> getRoomTrash() {
        return this.trash;
    }

    public Trashbin getTrashbin() {
        return trashbin;
    }

    public String getTrashbinDescription() {
        return trashbin.getDescription();
    }

    @Override
    public String toString() {
        return description;
    }
}