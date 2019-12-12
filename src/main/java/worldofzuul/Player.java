package worldofzuul;

public class Player implements Comparable<Player> {
    private String name;
    private String location;
    private int bestScore;
    private int score;
    private int tries;

    public Player(String name, int score, int bestScore, String location, int tries) {
        this.name = name;
        this.location = location;
        this.bestScore = bestScore;
        this.score = score;
        this.tries = tries;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getTries() {
        return tries;
    }

    @Override
    public int compareTo(Player p) {
        return p.getBestScore() - this.bestScore;
    }

    @Override
    public String toString() {
        return "Navn: " + name + "\n" +
                "Sidste score: " + score + "\n" +
                "Bedste score: " + bestScore + "\n" +
                "Sidst været: " + location + "\n" +
                "Gennemspilninger: " + tries + "\n" +
                "------------------------" + "\n";
    }
}
