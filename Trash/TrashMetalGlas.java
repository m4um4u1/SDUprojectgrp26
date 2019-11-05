package Trash;

public class TrashMetalGlas extends Trash{
    private final int trashType = 3;

    public TrashMetalGlas(int id, String name) {
        super(id, name, "");
    }

    public TrashMetalGlas(int id, String name, String description) {
        super(id, name, description);
    }


    public int getTrashType() {
        return trashType;
    }
}
