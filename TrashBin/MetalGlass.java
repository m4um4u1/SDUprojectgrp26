package TrashBin;


import worldofzuul.Room;

public class MetalGlass extends TrashBin {
    private final int trashtype = 3;

    public MetalGlass(Room Place, int id, String trash) {
        super(Place, id, trash);
    }

    @Override
    public String getTrash() {
        return super.getTrash();
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
