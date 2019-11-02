package TrashBin;
import Trash.Trash;

//SuperClass
public class TrashBin {
    
    //attributter
    private String Place;
    private int id;

    //constructor
    public TrashBin(int id, String Place) {
        this.id = id;
        this.Place = Place;
    }

    //metoder
    public String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
        this.Place = Place;
    }
}
