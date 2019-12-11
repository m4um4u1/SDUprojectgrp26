package worldofzuul;

public class Player implements Comparable<Player> {
    private String name;
    private String location;
    private int score;
    private int tries;

    public Player(String name, int score, String location, int tries) {
        this.name = name;
        this.location = location;
        this.score = score;
        this.tries = tries;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getTries() {
        return tries;
    }

    @Override
    public int compareTo(Player p) {
        return p.getScore() - this.score;
    }

    @Override
    public String toString() {
        return "Navn: " + name + "\n" +
                "Score: " + score + "\n" +
                "Sidst været: " + location + "\n" +
                "Forsøg: " + tries + "\n" +
                "-------------" + "\n";
    }
}
