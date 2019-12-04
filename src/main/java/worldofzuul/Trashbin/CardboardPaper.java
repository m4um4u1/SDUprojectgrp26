package worldofzuul.Trashbin;

import worldofzuul.Room;

public class CardboardPaper extends Trashbin {
    private final int trashtype = 2;

    public CardboardPaper(Room place, int id, String trash) {
        super(place, id, trash);
    }

    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }


    @Override
    public int getTrashtype() {
        return trashtype;
    }
}
