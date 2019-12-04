package Interface;

import worldofzuul.Trash.Trash;
import java.io.FileNotFoundException;

public interface IGame {
    void printInventory();
    void newUser(String name);
    String getOutput();
    void quit() throws FileNotFoundException;
    IMetadata getMd();
    void grabTrash(String id);
    String inspectTrash(String id);
    void depositTrash(Trash trash);
}
