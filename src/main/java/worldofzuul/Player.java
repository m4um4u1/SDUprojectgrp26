package worldofzuul;

public class Player {
    private int id;
    private String name;
    private int score;
    private String location;

    public Player(int id, String name, int score, String location) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.location = location;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Navn: " + name + '\n' +
                "Score: " + score + "\n"+
                "Sidst været: " + location + "\n";
    }
}
