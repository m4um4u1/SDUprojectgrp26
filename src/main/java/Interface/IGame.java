package Interface;

import worldofzuul.Room;

public interface IGame {

    void printInventory();

    void newUser(String name);
    
    void grabTrash(String id);

    void inspectTrash(String id);

    Room goRoom(String description);

    boolean winChecker();

    void depositTrash(Trash trash) throws FileNotFoundException;

    IMetadata getMd();

    void quit() throws FileNotFoundException;
}
