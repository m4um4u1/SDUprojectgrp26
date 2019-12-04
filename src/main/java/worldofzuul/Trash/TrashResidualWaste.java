package worldofzuul.Trash;

public class TrashResidualWaste extends Trash {
    private final int trashType = 4;

    public TrashResidualWaste(String id, String name, String description, String feedback) {
        super(id, name, description, feedback);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
