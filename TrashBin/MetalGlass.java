package TrashBin;


public class MetalGlass extends TrashBin {
    private final int trashtype = 3;
    
    public MetalGlass(int id, String Place) {
        super(id,Place);
    }
    
    @Override
    public void setPlace(String place) {
        super.setPlace(place);
        
    }

    public int getTrashtype() {
        return trashtype;
    }
    
}
