package Trash;

public class TrashResidualWaste extends Trash {
    private final int trashType = 4;

    public TrashResidualWaste(int id, String name) {
        super(id, name);
    }

    public TrashResidualWaste(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public int getTrashType() {
        return trashType;
    }
}
