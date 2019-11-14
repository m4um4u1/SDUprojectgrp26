package TrashBin;

import worldofzuul.Room;

public class Plastic extends TrashBin {
    private final int trashtype = 1;

    public Plastic(Room place, int id, String trash) {
        super(place, id, trash);
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
    public String getDescription() {
        return super.getDescription();
    }
}
