package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {
    String readScore();
    void updateScore(int score);
    int getScore();
    void newUser(String playerName);
    void flushData(String currentRoom) throws FileNotFoundException;
    String getOutput();
    void readCSV();
    String getCSV();
    void quit() throws FileNotFoundException;
}
