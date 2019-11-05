package Trash;

public class TrashOrganic extends Trash {
    private final int trashType = 5;

    public TrashOrganic(int id, String name) {
        super(id, name, "");
    }

    public TrashOrganic(int id, String name, String description) {
        super(id, name, description);
    }

    public int getTrashType() {
        return trashType;
    }
}
