package worldofzuul;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import Interface.IGame;
import Interface.IMetadata;
import worldofzuul.Trash.*;
import worldofzuul.Trashbin.*;

// Added an arraylist of inventory
public class Game implements IGame {
    private Room currentRoom;
    private ArrayList<Trash> inventory = new ArrayList<Trash>();
    private ArrayList<Trash> trashList; // Used to store Trash in a Room
    private Room livingRoom, kitchen, homeOffice, entre, driveway;
    private IMetadata md = new Metadata();

    public Game() {
        createRooms();
    }

    // Our rooms - which room to start in?
    public void createRooms() {
        livingRoom = new Room("i stuen", new ResidualWaste(livingRoom, 4, "Der er en skraldespand til restaffald "), "LIVINGROOM");
        kitchen = new Room("i kÃ¸kkenet", new Organic(kitchen, 5, "Der er en skraldespand til organisk"), "KITCHEN");
        homeOffice = new Room("pÃ¥ kontoret", new CardboardPaper(homeOffice, 2, "Der er en skraldespand til pap/papir"), "HOMEOFFICE");
        entre = new Room("i entreen", new Plastic(entre, 1, "Der er en skraldespand til plastik"), "ENTRE");
        driveway = new Room("i indkÃ¸rslen", new MetalGlass(driveway, 3, "Der er en skraldespand til metal/glas"), "DRIVEWAY");

        // Adds worldofzuul.Trash into each Room object.
        livingRoom
                .addTrash(new TrashMetalGlas("jakabov", "jakabov", "KonservedÃ¥sen er desvÃ¦rre tom men lugter ikke.", "Denne dÃ¥se er lavet af Metal og skal derfor i metal og glas-spanden hvis den er rent."))
                .addTrash(new TrashPaperCardboard("brochure", "brochure", "\"Guide til affaldssortering\", det har vi ikke brug for.", "Den er lavet af papir og skal i papir og pap-spanden."))
                .addTrash(new TrashMetalGlas("wineBottle", "vinflaske", "Den er tom, mor har drukket igen, Ã¸v...", "Den er lavet af glas, sÃ¥ den skal i glas og metal-spanden."));
        homeOffice
                .addTrash(new TrashResidualWaste("pringles", "pringlesrÃ¸r", "Den er tom og fÃ¸les som pap.", "Den skal i restaffaldsspanden fordi der er metal i indersiden og det derfor er blandet materiale."))
                .addTrash(new TrashPaperCardboard("envelope", "kuvert", "Kuverten har et lille plastikvindue.", "Selvom der er plastik i kuverten skal den i papir og pap-spanden."));
        kitchen
                .addTrash(new TrashPlastic("butterLid", "smÃ¸rlÃ¥g", "Den er helt ren.", "Den skal i plastikspanden, fordi den er ren og lavet af plast."))
                .addTrash(new TrashOrganic("carrots", "gulerod", "Den er orange, halv spist og allerede tÃ¸r", "Den skal i madaffaldsspanden, fordi der en madrest."))
                .addTrash(new TrashResidualWaste("eggBox", "Ã¦ggebakke", "Der har vÃ¦ret et knÃ¦kket Ã¦g i bakken, ad!", "Fordi at pappen er snavset med gammel Ã¦grest, skal den i restaffaldsspanden."));
        entre
                .addTrash(new TrashResidualWaste("pizzaBox", "pizzabakke", "Der er stadig tomatsovs og ost i bakken.", "Fordi der stadig er madrester pÃ¥ papbakken, skal det i restaffaldsspanden."))
                .addTrash(new TrashOrganic("pizzaSlices", "pizzaslice", "Dejlig hÃ¥rd med svamp.", "Det skal i madaffaldsspanden fordi det er gammelt mad."))
                .addTrash(new TrashMetalGlas("beerCan", "Ã¸ldÃ¥se", "De er alle tomme og importert fra Flensborg.", "De skal i metal og glas-spanden, da aluminium er et metal."));
        driveway
                .addTrash(new TrashOrganic("bananaPeel", "bananskræl", "Meget brun, pas pÃ¥ du ikke falder.", "Det skal i madaffaldsspanden fordi det er en madrest."))
                .addTrash(new TrashPlastic("straw", "sugerÃ¸r", "En rund cylinder, lavet af plast.", "Den skal i plastikaffald fordi den er lavet af plast."));


        // Set doors/exits for each room
        driveway.setExit("north", entre);

        entre.setExit("south", driveway);
        entre.setExit("west", homeOffice);
        entre.setExit("north", livingRoom);

        homeOffice.setExit("east", entre);

        livingRoom.setExit("south", entre);
        livingRoom.setExit("east",kitchen);

        kitchen.setExit("west", livingRoom);
        
        //which room to spawn
        currentRoom = driveway;
    }

    // Method to print the inventory - prints the String as well as the trash held
    public void printInventory() {
        String output = "";
        if (inventory.size() != 0) {
            for (Trash item : inventory) {
                output += item.getName() + ", ";
            }
            System.out.println("Din rygsÃ¦k indeholder: ");
            System.out.println(output);

        } else {
            System.out.println("Din rygsÃ¦k er tom.");
        }
    }

    public ArrayList<Trash> getInventory() {
        return inventory;
    }

    public IMetadata getMd() {
        return md;
    }
    // Gets the score from Metadata end prints it

    @Override
    public String inspectTrash(String id) {
        this.trashList = currentRoom.getRoomTrash();
        System.out.println("Trying to inspect: ");
        for (int i = 0; i < trashList.size(); i++) {
            if (id.equals(trashList.get(i).getId())) {
                System.out.println(trashList.get(i).getDescription());
                return trashList.get(i).getDescription();
            }
        }
        System.out.println("No Trash with that id found! Throwing NullPointException Error in inspectTrash");
        return null;
    }

    @Override
    public void grabTrash(String id) {
        this.trashList = currentRoom.getRoomTrash();
        for (int i = 0; i < trashList.size(); i++) {
            if (id.equals(trashList.get(i).getId())) {
                inventory.add(trashList.get(i));
                currentRoom.removeTrash(trashList.get(i).getName());
            } else if (i >= trashList.size() - 1) {
                System.out.println("Error in: " + id + ". Check " + currentRoom.getShortDescription() + "s controller and html");
                System.out.println("TRASH NOT FOUND! CRASHING NOW... BYE BYE");
                // Implement a way to exit the game and post crash screen
            }
        }
    }

    public boolean winChecker(){
        if(inventory.size() + trashList.size() == 0) {
        }
       return true;
    }

    public void depositTrash(Trash trash) throws FileNotFoundException {
        System.out.println(trash.getTrashType() + currentRoom.getTrashbin().getTrashtype());
        if (currentRoom.getTrashbin().getTrashtype() == trash.getTrashType()) {
            md.updateScore(100);
            System.out.println("Correct!");
        } else {
            md.updateScore(-50);
            System.out.println("Wrong!");
        }
        // Hvis det ikke virker sÃƒÂ¥ lav en custom equals
        inventory.remove(trash);
        md.winCondition();
    }

    @Override
    public Room goRoom(String direction) {
       Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("Der er ikke nogen dør!");
            return null;
        } else {
            this.currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
        return nextRoom;
    }

    // Saves the data for now
    public void quit() throws FileNotFoundException {
        md.setCurrentRoom(this.currentRoom.toString());
        md.quit();
    }
    
    
}