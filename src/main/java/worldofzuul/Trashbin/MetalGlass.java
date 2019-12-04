package worldofzuul.Trashbin;

import worldofzuul.Room;

public class MetalGlass extends Trashbin {
    private final int trashtype = 3;

    public MetalGlass(Room place, int id, String trash) {
        super(place, id, trash);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
    }

    @Override
    public int getTrashtype() {
        return trashtype;
    }
}
