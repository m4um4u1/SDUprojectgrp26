package TrashBin;

import worldofzuul.Room;

public class Organic extends TrashBin {
    private final int trashtype = 5;

    public Organic(Room Place, int id, String trash) {
        super(Place, id, trash);
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
    public String getTrash() {
        return super.getTrash();
    }
}
