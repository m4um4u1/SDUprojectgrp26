package TrashBin;

import worldofzuul.Room;

//SuperClass
public abstract class TrashBin {
    //attributter
    private Room place;
    private int id;
    private String description;

    //constructor
    public TrashBin(Room Place, int id, String trash) {
        this.place = Place;
        this.id = id;
        this.description = trash;
    }

    //metoder
    public Room getPlace() {
        return place;
    }

    public void setPlace(Room Place) {
        this.place = Place;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public abstract int getTrashtype();
}
