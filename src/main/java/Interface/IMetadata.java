package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {
    void updateScore(int score);
    String checkUser(String playerName);
    void quit() throws FileNotFoundException;
    String formatScore();
    void setCurrentRoom(String currentRoom);
    void loadPlayers();
}
