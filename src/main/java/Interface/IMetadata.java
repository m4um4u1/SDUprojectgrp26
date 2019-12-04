package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {

    void updateScore(int score);

    void quit() throws FileNotFoundException;

    void setCurrentRoom(String currentRoom);

    String checkUser(String playerName);

    String formatScore();

    void winCondition() throws FileNotFoundException;
}
