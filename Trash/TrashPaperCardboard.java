package Trash;

public class TrashPaperCardboard extends Trash {
    private final int trashType = 2;

    public TrashPaperCardboard(int id, String name) {
        super(id, name);
    }

    public TrashPaperCardboard(int id, String name, String description) {
        super(id, name, description);
    }

    public int getTrashType() {
        return trashType;
    }
}