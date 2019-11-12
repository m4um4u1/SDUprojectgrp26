package Trash;

public class TrashMetalGlas extends Trash {
    private final int trashType = 3;

    public TrashMetalGlas(int id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    public TrashMetalGlas(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
