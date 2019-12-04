package worldofzuul.Trash;

public class TrashOrganic extends Trash {
    private final int trashType = 5;

    public TrashOrganic(String id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
