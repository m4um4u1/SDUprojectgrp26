package worldofzuul.Trash;

public class TrashPaperCardboard extends Trash {
    private final int trashType = 2;

    public TrashPaperCardboard(String id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
