package worldofzuul;


import java.util.ArrayList;

import Trash.*;
import Trashbin.*;


//Added an arraylist of inventory
public class Game {
    private Room currentRoom;
    ArrayList<Trash> inventory = new ArrayList<Trash>();
    Room livingRoom, kitchen, homeOffice, entre, driveway;


    public Game() {
        createRooms();

    }

    //Our rooms - which room to start in?
    private void createRooms() {
        livingRoom = new Room("i stuen", new ResidualWaste(livingRoom, 4, "Der er en skraldespand til restaffald "));
        kitchen = new Room("i køkkenet", new Organic(kitchen, 5, "Der er en skraldespand til organisk"));
        homeOffice = new Room("på kontoret", new CardboardPaper(homeOffice, 2, "Der er en skraldespand til pap/papir"));
        entre = new Room("i entreen", new Plastic(entre, 1, "Der er en skraldespand til plastik"));
        driveway = new Room("udenfor i indkørslen", new MetalGlass(driveway, 3, "Der er en skraldespand til metal/glas"));

        //Adds Trash into each Room object.
        livingRoom
                .addTrash(new TrashMetalGlas(1, "jakabov", "Konservedåsen er desværre tom men lugter ikke.", "Denne dåse er lavet af Metal og skal derfor i metal og glas-spanden hvis den er rent."))
                .addTrash(new TrashPaperCardboard(2, "brochure", "\"Guide til affaldssortering\", det har vi ikke brug for.", "Den er lavet af papir og skal i papir og pap-spanden."))
                .addTrash(new TrashMetalGlas(9, "vinflaske", "Den er tom, mor har drukket igen, øv...", "Den er lavet af glas, så den skal i glas og metal-spanden."));
        homeOffice
                .addTrash(new TrashResidualWaste(3, "pringlesrør", "Den er tom og føles som pap.", "Den skal i restaffaldsspanden fordi der er metal i indersiden og det derfor er blandet materiale."))
                .addTrash(new TrashPaperCardboard(10, "kurvert", "Kuverten har et lille plastikvindue.", "Selvom der er plastik i kuverten skal den i papir og pap-spanden."));
        kitchen
                .addTrash(new TrashPlastic(4, "smørlåg", "Den er helt ren.", "Den skal i plastikspanden, fordi den er ren og lavet af plast."))
                .addTrash(new TrashOrganic(11, "gulerod", "Den er orange, halv spist og allerede tør", "Den skal i madaffaldsspanden, fordi der en madrest."))
                .addTrash(new TrashResidualWaste(12, "æggebakke", "Der har været et knækket æg i bakken, ad!", "Fordi at pappen er snavset med gammel ægrest, skal den i restaffaldsspanden."));
        entre
                .addTrash(new TrashResidualWaste(5, "pizzabakke", "Der er stadig tomatsovs og ost i bakken.", "Fordi der stadig er madrester på papbakken, skal det i restaffaldsspanden."))
                .addTrash(new TrashOrganic(6, "pizzaslice", "Dejlig hård med svamp.", "Det skal i madaffaldsspanden fordi det er gammelt mad."))
                .addTrash(new TrashMetalGlas(13, "øldåser", "De er alle tomme og importert fra Flensborg.", "De skal i metal og glas-spanden, da aluminium er et metal."));
        driveway
                .addTrash(new TrashOrganic(7, "bananskræl", "Meget brun, pas på du ikke falder.", "Det skal i madaffaldsspanden fordi det er en madrest."))
                .addTrash(new TrashPlastic(8, "sugerør", "En rund cylinder, lavet af plast.", "Den skal i plastikaffald fordi den er lavet af plast."));

        //set doors/exits for each room
        driveway.setExit("nord", entre);

        entre.setExit("syd", driveway);
        entre.setExit("vest", homeOffice);
        entre.setExit("nord", livingRoom);

        homeOffice.setExit("øst", entre);

        livingRoom.setExit("syd", entre);
        livingRoom.setExit("øst", kitchen);

        kitchen.setExit("vest", livingRoom);
        //which room to spawn
        currentRoom = driveway;
    }

    //Method to print the inventory - prints the String as well as the trash held
    private void printInventory() {
        String output = "";
        if (inventory.size() != 0) {
            for (Trash item : inventory) {
                output += item.getName() + ", ";

            }
            System.out.println("Din rygsæk indeholder: ");
            System.out.println(output);

        } else {
            System.out.println("Din rygsæk er tom.");
        }
    }
}


   /* private void inspectTrash(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Undersøg hvad?");
            return;
        }
        String trash = command.getSecondWord();
        for (Trash item : inventory) {
            if (item.getName().equals(trash)) {
                System.out.println(item.getDescription());
            } else {
                System.out.println(trash + " ligger ikke i din Ryksæk");
            }
        }
    }

    private void depositTrash(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Smid hvad?");
            return;
        }
        String trash = command.getSecondWord();
        // Iterates the Trash inventory:
        // (Had to use an iterator in order to be able to remove from inventory after depositing (ConcurrentModificationException))
        for (Iterator<Trash> itr = inventory.iterator(); itr.hasNext();) {
            Trash t = itr.next();
            // Checks if inventory trash String name matches requested trash String name:
            if (t.getName().equals(trash)) {
                System.out.println("Du smed " + trash + " ud fra din rygsæk.");
                // Checks if requested inventory trash type matches room bin trash type:
                if (t.getTrashType() == (currentRoom.getTrashbin()).getTrashtype()) {
                    System.out.println("Det er den rigtige skraldespand! Du fik 100 point!");
                    metaData.updateScore(100);
                } else {
                    System.out.println("Hov! Det virker ikke rigtigt ... "+ t.getFeedback() +" Du tabte 50 point :(.");
                    metaData.updateScore(-50);
                }
                itr.remove();
            } else {
                System.out.println(trash + " har du ikke i din rygsæk!");
            }
        }
    }

    private void printHelp() {
        System.out.println("Du kigger forvirret rundt i huset.. der er affald i alle rum...");
        System.out.println();
        System.out.println("Dine kommandoer er:");
    }

    private void goRoom() {

       Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("Der er ikke nogen dør!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(currentRoom.getTrashbinDescription());//skraldspand
        }
    }

    */

   /* public boolean quit() {
        metaData.flushData(currentRoom.getShortDescription());
            return true;
        }
    }
*/


