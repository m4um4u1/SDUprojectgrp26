package TrashBin;


import worldofzuul.Room;

public class Plastic extends TrashBin {
    private final int trashtype = 1;

    public Plastic(Room Place, int id, String trash) {
        super(Place, id, trash);
    }

    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
    }

    @Override
    public int getTrashtype() {
        return trashtype;
    }

    @Override
    public String getTrash() {
        return super.getTrash();
    }
    
    

}
