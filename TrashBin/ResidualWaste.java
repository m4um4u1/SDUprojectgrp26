package TrashBin;


import worldofzuul.Room;

public class ResidualWaste extends TrashBin {
    private final int trashtype = 4;

    public ResidualWaste(int id, Room Place) {
        super(id,Place);
    }

    @Override
    public void setPlace(Room Place) {
        super.setPlace(Place); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTrashtype() {
        return trashtype;
    }
    
    
}
