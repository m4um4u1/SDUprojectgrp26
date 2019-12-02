package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {
    void updateScore(int score);
    int getScore();
    String newUser(String playerName);
    void quit() throws FileNotFoundException;
    String formatScore();
    void setPlayerName(String playerName);
}
