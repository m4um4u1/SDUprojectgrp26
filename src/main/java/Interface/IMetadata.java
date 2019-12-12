package Interface;

import java.io.FileNotFoundException;

public interface IMetadata {

    int getScore();

    void updateScore(int score);

    String formatScore();
    
    void quit() throws FileNotFoundException;

    void setCurrentRoom(String currentRoom);

    String checkUser(String playerName);

    void winConditionIncrementer();
    
    boolean winConditionChecker() throws FileNotFoundException;

}

