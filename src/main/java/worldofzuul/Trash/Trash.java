package worldofzuul.Trash;

public abstract class Trash {

    private int id;
    private String name;
    private String description;
    private String feedback;

    public Trash(int id, String name, String description, String feedback) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public abstract int getTrashType();


}
