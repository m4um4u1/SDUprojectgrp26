package TrashBin;


import worldofzuul.Room;

public class CardboardPaper extends TrashBin {
    private final int trashtype = 2;

    public CardboardPaper(int id, Room place) {
        super(id,place);
    }
    @Override
    public void setPlace (Room place) {
        super.setPlace(place);
    }

    public int getTrashtype() {
        return trashtype;
    }
    
}
