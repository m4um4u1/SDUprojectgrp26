package worldofzuul.Trashbin;

import worldofzuul.Room;

public class ResidualWaste extends Trashbin {
    private final int trashtype = 4;

    public ResidualWaste(Room place, int id, String trash) {
        super(place, id, trash);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setPlace(Room Place) {
        super.setPlace(Place);
    }

    @Override
    public int getTrashtype() {
        return trashtype;
    }
}
