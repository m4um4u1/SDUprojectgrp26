package worldofzuul;

public class Player implements Comparable<Player> {
    private String name;
    private int score;
    private String location;

    public Player(String name, int score, String location) {
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
        return  "Navn: " + name + "\n" +
                "Score: " + score + "\n" +
                "Sidst været: " + location +"\n" +
                "-------------" + "\n";
    }
    
    @Override
    public int compareTo(Player p) {
        return p.getScore() - this.score;
    }
}
