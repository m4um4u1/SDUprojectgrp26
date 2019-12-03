package Interface;

import java.io.FileNotFoundException;

public interface IGame {

    void printInventory();
    void quit() throws FileNotFoundException;
    IMetadata getMd();
}
