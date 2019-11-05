package TrashBin;


import worldofzuul.Room;

public class Plastic extends TrashBin {
    private final int trashtype = 1;

    public Plastic(int id, Room Place) {
        super(id, Place);
    }

    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
    }

    public int getTrashtype() {
        return trashtype;
    }

}
