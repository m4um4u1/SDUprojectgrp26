package TrashBin;


public class Plastic extends TrashBin {
    private final int trashtype = 1;

    public Plastic(int id, String Place) {
        super(id,Place);
    }
    
    @Override
    public void setPlace(String place) {
        super.setPlace(place);
    }
}
