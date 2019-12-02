package worldofzuul.Trash;

public class TrashMetalGlas extends Trash {
    private final int trashType = 3;

    public TrashMetalGlas(String id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
