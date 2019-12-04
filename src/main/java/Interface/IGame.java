package Interface;

import worldofzuul.Room;

public interface IGame {
    void printInventory();
    void newUser(String name);
    String getOutput();
    void grabTrash(String id);
    void inspectTrash(String id);
    Room goRoom(String description);
}
