package Trash;

public class TrashPaperCardboard extends Trash {
    private final int trashType = 2;

    public TrashPaperCardboard(int id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    public TrashPaperCardboard(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }

}
