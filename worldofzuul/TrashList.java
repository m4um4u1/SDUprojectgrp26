package worldofzuul;

import Trash.*;
import java.util.ArrayList;

public class TrashList {
     ArrayList<Trash> trashList = new ArrayList<Trash>();
     
     //Functions as a comma seperated file.
     //Format is: name, description, trashType 
     //1 - plastic, 2 - paper and cardboard, 3 - metal and glass, 4 - residual waste, 5 - Organic
     //Remember to sort by trashType (1 at top, 5 at bottom).
     //This can be expanded to read from a text file (thus allowing users to add more items or edit current items)
     String trash = ""
             + "mælkelåg,mangler kartonen,1,"
             + "toiletrulle,der er ikke mere papir,2,"
             + "syltetøjsglas,den er ren,3,"
             + "pizzabakke,der er ikke mere pizza ;(,4,"
             + "banan,sort og ulækker,5"
             ;
    
     public TrashList() {
    //Splits the string trash at comma and saves each element in a string array.
    String[] trashSplit = trash.split(",");
    
    
    for (int i = 0; i < trashSplit.length/3; i++) {
        String name = trashSplit[i*3];
        String description = trashSplit[i*3+1];
        int id = i+1;
        
        //First we check what type of trash it is. Then a new object corresponding to the trashType is created and added to the trashList.
        switch(Integer.parseInt(trashSplit[i*3+2])) {
            case 1:
                trashList.add(new TrashPlastic(id,name,description));
                break;
            case 2:
                trashList.add(new TrashPaperCardboard(id,name,description));
                break;
            case 3:
                trashList.add(new TrashMetalGlas(id,name,description));
                break;
            case 4:
                trashList.add(new TrashResidualWaste(id,name,description));
                break;
            case 5:
                trashList.add(new TrashOrganic(id,name,description));
                break;
        }
    }
}
    
    public String printList() {
        String returnString = null;
        
        for (Trash i : trashList) {
            returnString += i + "\n";
            System.out.println(i);
        }
        return returnString;
    }
    
    
}

