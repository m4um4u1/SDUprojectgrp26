package Data;

import Interface.IDataRaW;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRaW implements IDataRaW {
private File file = new File("metadata.csv");
    //this will read the whole CSV and put it in a array
    @Override
    public ArrayList<String> readCSV() {
    ArrayList<String> scoreArray = null;
    try {
        scoreArray = new ArrayList<String>();
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            scoreArray.add(fileReader.next());
        }
        fileReader.close();
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
    return scoreArray;
}
    // Gets the current room's short description when the program is quit:
    @Override
    public void saveCSV(ArrayList<String> scoreArray) {
    try {
    // Writes the ArrayList to the metadata.csv file:
    PrintWriter fileWriter = new PrintWriter(file);
    for (String s : scoreArray) {
        fileWriter.println(s);
    }
    fileWriter.close();
    } catch (FileNotFoundException e){
        System.out.println("File not found");
        }
    }

}