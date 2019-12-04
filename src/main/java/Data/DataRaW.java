package Data;

import Interface.IDataRaW;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRaW implements IDataRaW {
    private File file = new File("metadata.csv");

    // Reads the whole CSV and puts it in an array:
    @Override
    public ArrayList<String> readCSV() {
        file.setReadable(true); // makes it harder to manipulate highscore
        ArrayList<String> scoreArray = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                scoreArray.add(fileReader.nextLine());
            }
            fileReader.close();
            file.setReadable(false);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return scoreArray;
    }

    // Gets the current room's short description when the program is quit:
    @Override
    public void saveCSV(ArrayList<String> scoreArray) {
        // Writes the ArrayList to the metadata.csv file:
        try {
            file.setWritable(true); // makes it harder to manipulate highscore
            PrintWriter fileWriter = new PrintWriter(file);
            for (String s : scoreArray) {
                fileWriter.println(s);
            }
            file.setWritable(false);
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

}