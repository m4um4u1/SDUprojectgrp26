package Trash;

public class TrashPlastic extends Trash{
    private final int trashType = 1;

    public TrashPlastic(int id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    public TrashPlastic(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
