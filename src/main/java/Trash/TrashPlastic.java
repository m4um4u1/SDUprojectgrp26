package Trash;

public class TrashPlastic extends Trash {
    private final int trashType = 1;

    public TrashPlastic(int id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
