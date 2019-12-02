package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {
    void updateScore(int score);
    int getScore();
    void newUser(String playerName);
    String getOutput();
    void quit() throws FileNotFoundException;
    String formatScore();
}
