package Interface;

import java.util.ArrayList;

public interface IDataRaW {
    // Read the whole CSV and puts it in an array:
    ArrayList<String> readCSV();

    void saveCSV(ArrayList<String> scoreArray); //Saves the players data from the Array to a CSV file.
}
