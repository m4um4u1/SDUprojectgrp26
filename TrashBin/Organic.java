package TrashBin;

import worldofzuul.Room;

public class Organic extends TrashBin {
    private final int trashtype = 5;

    public Organic(Room place, int id, String trash) {
        super(place, id, trash);
    }

    @Override
    public void setPlace(Room Place) {
        super.setPlace(Place);
    }

    @Override
    public int getTrashtype() {
        return trashtype;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
