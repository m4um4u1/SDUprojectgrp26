package TrashBin;


import worldofzuul.Room;

public class MetalGlass extends TrashBin {
    private final int trashtype = 3;
    
    public MetalGlass(int id, Room Place) {
        super(id,Place);
    }
    
    @Override
    public void setPlace(Room place) {
        super.setPlace(place);
        
    }
    public int getTrashtype() {
        return trashtype;
    }
    
}
