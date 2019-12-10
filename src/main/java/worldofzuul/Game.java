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
    private Room livingRoom, kitchen, homeOffice, entre, driveway, bathroom;
    private IMetadata md = new Metadata();

    public Game() {
        createRooms();
    }

    // Our rooms - which room to start in?
    public void createRooms() {
        livingRoom = new Room("i stuen", new ResidualWaste(livingRoom, 4, "Der er en skraldespand til restaffald "), "LIVINGROOM");
        kitchen = new Room("i køkkenet", new Organic(kitchen, 5, "Der er en skraldespand til organisk"), "KITCHEN");
        homeOffice = new Room("på kontoret", new CardboardPaper(homeOffice, 2, "Der er en skraldespand til pap/papir"), "HOMEOFFICE");
        entre = new Room("i entreen", new Plastic(entre, 1, "Der er en skraldespand til plastik"), "ENTRE");
        driveway = new Room("i indkørslen", new MetalGlass(driveway, 3, "Der er en skraldespand til metal/glas"), "DRIVEWAY");
        bathroom = new Room("i badeværslet", "BATHROOM");

        // Adds worldofzuul.Trash into each Room object.
        livingRoom
                .addTrash(new TrashMetalGlas("jakabov", "jakabov", "Konservedåsen er desværre tom men lugter ikke.", "Denne dåse er lavet af metal og skal derfor i metal og glas-spanden hvis den er ren."))
                .addTrash(new TrashPaperCardboard("brochure", "brochure", "\"Guide til affaldssortering\", det har vi ikke brug for.", "Den er lavet af papir og skal i papir og pap-spanden."))
                .addTrash(new TrashMetalGlas("wineBottle", "vinflaske", "Den er tom, mor har drukket igen, Øv...", "Den er lavet af glas, så den skal i glas og metal-spanden."));
        homeOffice
                .addTrash(new TrashResidualWaste("pringles", "pringlesrør", "Den er tom og føles som pap.", "Den skal i restaffaldsspanden fordi der er metal i indersiden og det derfor er blandet materiale."))
                .addTrash(new TrashPaperCardboard("envelope", "kuvert", "Kuverten har et lille plastikvindue.", "Selvom der er plastik i kuverten skal den i papir og pap-spanden."));
        kitchen
                .addTrash(new TrashPlastic("butterLid", "smørlåg", "Den er helt ren.", "Den skal i plastikspanden, fordi den er ren og lavet af plast."))
                .addTrash(new TrashOrganic("carrots", "gulerod", "Den er orange, halv spist og allerede tør", "Den skal i madaffaldsspanden, fordi der en madrest."))
                .addTrash(new TrashResidualWaste("eggBox", "æggebakke", "Der har været et knækket æg i bakken, ad!", "Fordi at pappet er snavset til med gammel æg, skal den i restaffaldsspanden."));
        entre
                .addTrash(new TrashResidualWaste("pizzaBox", "pizzabakke", "Der er stadig tomatsovs og ost i bakken.", "Fordi der stadig er madrester på papbakken, skal det i restaffaldsspanden."))
                .addTrash(new TrashOrganic("pizzaSlices", "pizzaslice", "Dejlig hård med svamp.", "Det skal i madaffaldsspanden fordi det er gammelt mad."))
                .addTrash(new TrashMetalGlas("beerCan", "øldåse", "De er alle tomme og importert fra Flensborg.", "De skal i metal og glas-spanden, da aluminium er et metal."));
        driveway
                .addTrash(new TrashOrganic("bananaPeel", "bananskræl", "Meget brun, pas på du ikke falder.", "Det skal i madaffaldsspanden fordi det er en madrest."))
                .addTrash(new TrashPlastic("straw", "sugerør", "En rund cylinder, lavet af plast.", "Den skal i plastikaffald fordi den er lavet af plast."));
        bathroom
                .addTrash(new TrashResidualWaste("toothbrush", "tandbørste", "Den er brugt, og uhygiejnisk.", "Den skal i restaffaldsspanden, fordi det er uhygiejnisk"))
                .addTrash(new TrashPaperCardboard("toiletpaperroll", "toiletrulle", "Der er ingen papir tilbage på rullen", "Den skal i papirspanden fordi den er lavet af pap"));

        // Set doors/exits for each room
        driveway.setExit("north", entre);

        entre.setExit("south", driveway);
        entre.setExit("west", homeOffice);
        entre.setExit("north", livingRoom);

        homeOffice.setExit("east", entre);

        livingRoom.setExit("south", entre);
        livingRoom.setExit("east", kitchen);
        livingRoom.setExit("west", bathroom);

        bathroom.setExit("east", livingRoom);

        kitchen.setExit("west", livingRoom);
        
        //which room to spawn
        currentRoom = driveway;
        md.setCurrentRoom(currentRoom.getShortDescription());
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
    
    @Override
    public ArrayList<Trash> getTrashRoom() {
        return currentRoom.getRoomTrash();
    }

    public boolean depositTrash(Trash trash) throws FileNotFoundException {
        System.out.println(trash.getTrashType() + currentRoom.getTrashbin().getTrashtype());
        if (currentRoom.getTrashbin().getTrashtype() == trash.getTrashType()) {
            md.updateScore(100);
            inventory.remove(trash);
            md.winCondition();
            return true;
        } else {
            md.updateScore(-50);
            inventory.remove(trash);
            md.winCondition();
            return false;
        }
    }

    @Override
    public Room goRoom(String direction) {
       Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("Der er ikke nogen dør!");
            return null;
        } else {
            this.currentRoom = nextRoom;
            md.setCurrentRoom(currentRoom.getShortDescription());
        }
        return nextRoom;
    }

    // Saves the data for now
    @Override
    public void quit() throws FileNotFoundException {
        md.setCurrentRoom(this.currentRoom.toString());
        md.quit();
    }
    
    
}