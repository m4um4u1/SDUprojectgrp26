package Interface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import worldofzuul.Room;
import worldofzuul.Trash.Trash;

public interface IGame {

    void printInventory();
    
    void grabTrash(String id);

    String inspectTrash(String id);

    Room goRoom(String description);

    boolean winChecker();

    void depositTrash(Trash trash) throws FileNotFoundException;

    IMetadata getMd();

    void quit() throws FileNotFoundException;
    
    ArrayList<Trash> getInventory();
    
    ArrayList<Trash> getTrashRoom();
}
