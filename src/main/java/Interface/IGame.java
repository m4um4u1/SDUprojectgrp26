package Interface;

import worldofzuul.Trash.Trash;

public interface IGame {
    void printInventory();
    void newUser(String name);
    String getOutput();
    void grabTrash(String id);
    String inspectTrash(String id);
    void depositTrash(Trash trash);
}
