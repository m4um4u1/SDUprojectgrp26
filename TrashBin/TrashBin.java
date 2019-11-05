package TrashBin;
import worldofzuul.Room;

//SuperClass
public class TrashBin {
    
    //attributter
    private Room Place;
    public int id;

    //constructor
    public TrashBin(int id, Room Place) {
        this.id = id;
        this.Place = Place;
    }

    //metoder
    public Room getPlace() {
        return Place;
    }

    public void setPlace(Room Place) {
        this.Place = Place;
    }
}
