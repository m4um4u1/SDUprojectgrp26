package TrashBin;


public class CardboardPaper extends TrashBin {
    private final int trashtype = 2;

    public CardboardPaper(int id,String place) {
        super(id,place);
    }
    @Override
    public void setPlace (String place) {
        super.setPlace(place);
    }
    
}
