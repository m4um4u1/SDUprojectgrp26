package Interface;

import java.util.ArrayList;

public interface IDataRaW {
    //this will read the whole CSV and put it in a array
    ArrayList<String> readCSV();

    void saveCSV(ArrayList<String> scoreArray);
}
