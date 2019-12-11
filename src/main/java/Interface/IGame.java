package Interface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import worldofzuul.Room;
import worldofzuul.Trash.Trash;

public interface IGame {

    void grabTrash(String id);

    String inspectTrash(String id);

    boolean depositTrash(Trash trash) throws FileNotFoundException;
    
    Room goRoom(String description);

    IMetadata getMd();

    void quit() throws FileNotFoundException;
    
    ArrayList<Trash> getInventory();
    
    ArrayList<Trash> getTrashRoom();
}
