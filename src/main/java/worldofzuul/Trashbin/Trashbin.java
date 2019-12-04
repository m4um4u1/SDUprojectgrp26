package worldofzuul.Trashbin;

import worldofzuul.Room;

//SuperClass
public abstract class Trashbin {
    //attributter
    private Room place;
    private int id;
    private String description;

    //constructor
    public Trashbin(Room place, int id, String description) {
        this.place = place;
        this.id = id;
        this.description = description;
    }

    //metoder
    public void setPlace(Room place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public abstract int getTrashtype();
}