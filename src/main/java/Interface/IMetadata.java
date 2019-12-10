package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {

    int getScore();

    void updateScore(int score);

    void quit() throws FileNotFoundException;

    void setCurrentRoom(String currentRoom);

    String checkUser(String playerName);

    String formatScore();

    boolean winCondition() throws FileNotFoundException;

}

