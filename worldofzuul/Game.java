package worldofzuul;

import java.util.ArrayList;
import Trash.*;
import TrashBin.*;

import java.util.Iterator;

//Added an arraylist of inventory 
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    ArrayList<Trash> inventory = new ArrayList<Trash>();
    Room livingRoom, kitchen, homeOffice, entre, driveway;
    private Metadata metaData;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
        metaData = new Metadata();
    }

    //Our rooms - which room to start in?
    private void createRooms()
    {   
        //adds rooms
        livingRoom = new Room("in the living room", new ResidualWaste(livingRoom, 4, "test1"));
        kitchen = new Room("in the kitchen", new Organic(kitchen,5, "test5"));
        homeOffice = new Room("in the home office", new CardboardPaper(homeOffice, 2, "test2"));
        entre = new Room("in the entre",new Plastic(entre, 1, "test1"));
        driveway = new Room("outside in the driveway", new MetalGlass(driveway, 3, "test3"));

        //Adds Trash into each Room object.
        livingRoom  .addTrash(new TrashMetalGlas(       1,  "Jakabov",          "Konservedåsen er desværre tom men lugter ikke.",           "Denne dåse er lavet af Metal og skal derfor i metal og glas-spanden hvis den er rent."))
                    .addTrash(new TrashPaperCardboard(  2,  "Brochure",         "\"Guide til affaldssortering\", det har vi ikke brug for.","Den er lavet af papir og skal i papir og pap-spanden."))
                    .addTrash(new TrashMetalGlas(       9,  "Vinflaske",        "Den er tom, mor har drukket igen, øv...",                  "Den er lavet af glas, så den skal i glas og metal-spanden."));
        homeOffice  .addTrash(new TrashResidualWaste(   3,  "Pringlesrør",      "Den er tom og føles som pap.",                             "Den skal i restaffaldsspanden fordi der er metal i indersiden og det derfor er blandet materiale."))
                    .addTrash(new TrashPaperCardboard(  10, "Kurvert med rude", "Kuverten har et lille plastikvindue.",                     "Selvom der er plastik i kuverten skal den i papir og pap-spanden."));
        kitchen     .addTrash(new TrashPlastic(         4,  "Smørlåg",          "Den er helt ren.",                                         "Den skal i plastikspanden, fordi den er ren og lavet af plast."))
                    .addTrash(new TrashOrganic(         11, "Gulerod",          "Den er orange, halv spist og allerede tør",                "Den skal i madaffaldsspanden, fordi der en madrest."))
                    .addTrash(new TrashResidualWaste(   12, "Æggebakke",        "Der har været et knækket æg i bakken, ad!",                "Fordi at pappen er snavset med gammel ægrest, skal den i restaffaldsspanden."));
        entre       .addTrash(new TrashResidualWaste(   5,  "Pizzabakke",       "Der er stadig tomatsovs og ost i bakken.",                 "Fordi der stadig er madrester på papbakken, skal det i restaffaldsspanden."))
                    .addTrash(new TrashOrganic(         6,  "Pizzaslice",       "Dejlig hård med svamp.",                                   "Det skal i madaffaldsspanden fordi det er gammelt mad."))
                    .addTrash(new TrashMetalGlas(       13, "Øldåser",          "De er alle tomme og importert fra Flensborg.",             "De skal i metal og glas-spanden, da aluminium er et metal."));
        driveway    .addTrash(new TrashOrganic (        7,  "Bananskræl",       "Meget brun, pas på du ikke falder.",                       "Det skal i madaffaldsspanden fordi det er en madrest."))
                    .addTrash(new TrashPlastic (        8,  "Sugerør",          "En rund cylinder, lavet af plast.",                        "Den skal i plastikaffald fordi den er lavet af plast."));
        
        driveway.setExit("north", entre);

        entre.setExit("south", driveway);
        entre.setExit("west", homeOffice);
        entre.setExit("north", livingRoom);

        homeOffice.setExit("east", entre);

        livingRoom.setExit("south", entre);
        livingRoom.setExit("east", kitchen);

        kitchen.setExit("west", livingRoom);

        currentRoom = driveway;
    }

    public void play() 
    {            
        printWelcome();
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    //Adding another commandword - INVENTORY, printing the inventory
    //Adding another commandWord - GRAB, taking items
    //Adding another commandWord - INSPECT, inspecting items
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        }
        else if (commandWord == CommandWord.GRAB) {
            grabTrash(command);
        }

        else if (commandWord == CommandWord.DEPOSIT) {
            depositTrash(command);
        }
      
        else if (commandWord == CommandWord.INSPECT) {
            inspectTrash(command);
        }
        return wantToQuit;
    }
    
    //Method to print the inventory - prints the String as well as the trash held
    private void printInventory() {
        String output = "";
        for (Trash item : inventory) {
            output += item.getName() + " ";
        }
        System.out.println("Your inventory currently contains: ");
        System.out.println(output);
        
    }
    
    //Method to grab trash in the rooms and adding it to the inventory
    private void grabTrash(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Grab what?");
            return;
        }

        String trash = command.getSecondWord();

        Trash newTrash = currentRoom.getTrash(trash);

        if (newTrash == null) {
            System.out.println("That piece of trash is not here!");
        }
        else {
            inventory.add(newTrash);
            currentRoom.removeTrash(trash);
            System.out.println("Grabbed: " + trash);
        }
    }
    //method to inspect the trash from inventory
    private void inspectTrash(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Inspect what?");
            return;
        }
        String trash = command.getSecondWord();
        String output = "Det ligger ikke i din Rygsæk.";
        for (Trash item : inventory) {
            if (item.getName().equals(trash)) {
                output = item.getDescription();
            }
        }
        System.out.println(output);
    }
        
    private void depositTrash(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Deposit what?");
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
                if (t.getTrashType() == (currentRoom.getTrashBin()).getTrashtype()) {
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


    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            metaData.flushData(currentRoom.getShortDescription());
            return true;
        }
    }
  
}
