package TrashBin;

import worldofzuul.Room;

public class CardboardPaper extends TrashBin {
    private final int trashtype = 2;

    public CardboardPaper(Room Place, int id, String trash) {
        super(Place, id, trash);
    }

    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
    }

    @Override
    public String getTrash() {
        return super.getTrash();
    }


    @Override
    public int getTrashtype() {
        return trashtype;
    }
}
