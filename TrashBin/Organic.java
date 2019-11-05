package TrashBin;


public class Organic extends TrashBin {
    private final int trashtype = 5;
    
    public Organic(int id, String Place) {
        super(id,Place);
    }

    @Override
    public void setPlace(String Place) {
        super.setPlace(Place); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTrashtype() {
        return trashtype;
    }
    
    
    
}
