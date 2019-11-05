package TrashBin;
import worldofzuul.Room;

//SuperClass
public class TrashBin {
    
    //attributter
    private Room Place;
    public int id;
    private String trash;

    //constructor

    public TrashBin(Room Place, int id, String trash) {
        this.Place = Place;
        this.id = id;
        this.trash = trash;
    }

    //metoder
    public Room getPlace() {
        return Place;
    }

    public void setPlace(Room Place) {
        this.Place = Place;
    }

    public int getId() {
        return id;
    }

    public String getTrash() {
        return trash;
    }
    
    
}
