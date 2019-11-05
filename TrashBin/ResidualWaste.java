
package TrashBin;


import worldofzuul.Room;

public class ResidualWaste extends TrashBin {
    private final int trashtype = 4;

    public ResidualWaste(Room Place, int id, String trash) {
        super(Place, id, trash);
    }

    @Override
    public String getTrash() {
        return super.getTrash(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlace(Room Place) {
        super.setPlace(Place); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTrashtype() {
        return trashtype;
    }
    
    
}

package TrashBin;


import worldofzuul.Room;

public class ResidualWaste extends TrashBin {
    private final int trashtype = 4;

    public ResidualWaste(Room Place, int id, String trash) {
        super(Place, id, trash);
    }

    @Override
    public String getTrash() {
        return super.getTrash(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlace(Room Place) {
        super.setPlace(Place); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTrashtype() {
        return trashtype;
    }
    
    
}

