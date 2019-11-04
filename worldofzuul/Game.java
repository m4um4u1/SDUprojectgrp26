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
      
        livingRoom = new Room("in the living room");
        kitchen = new Room("in the kitchen");
        homeOffice = new Room ("in the home office");
        entre = new Room("in the entre");
        driveway = new Room ("outside in the driveway");
        
        driveway.setExit("north", entre);

        entre.setExit("south", driveway);
        entre.setExit("west", homeOffice);       

        homeOffice.setExit("east", entre);

        livingRoom.setExit("south", entre);
        livingRoom.setExit("east", kitchen);

        kitchen.setExit("west", livingRoom);

        currentRoom = driveway;
        
        
        //added these for testing
        entre.setTrash(new TrashPlastic(1,"name","description432"));
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
        return wantToQuit;
    }
    
    //Method to print the inventory - prints the String as well as the trash held
    private void printInventory() {
        String output = "";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getDescription() + " ";
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
        
    private void depositTrash(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Deposit what?");
            return;
        }
        
        String trash = command.getSecondWord();
        
        // Added this trash bin in the entre for deposit testing:
        Plastic test = new Plastic(1, entre.getShortDescription());
        // Adding trash bins to rooms like how setExit works might be ideal for full implementation.
        
        // Iterates the Trash inventory:
        // (Had to use an iterator in order to be able to remove from inventory after depositing (ConcurrentModificationException))
        for (Iterator<Trash> itr = inventory.iterator(); itr.hasNext();) {
            Trash t = itr.next();
            // Checks if inventory trash String name matches requested trash String name:
            if (t.getDescription().equals(trash)) { // Why are we grabbing trash by description atm? Change this to getName instead when changed I presume.
                System.out.println("You deposit " + trash + " from your inventory");
                // Checks if requested inventory trash type matches room bin trash type && current room is bin location:
                if (t.getTrashType() == test.getTrashtype() && currentRoom.getShortDescription().equals(test.getPlace())) {
                    System.out.println("That's the correct bin! You gained 100 points!");
                    metaData.updateScore(100);
                } else {
                    System.out.println("Whoops! That didn't seem right... You lost 1000000000 points LUUUL");
                    metaData.updateScore(-1000000000);
                }
                itr.remove();
            } else {
            System.out.println(trash + " was not found in inventory!");
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
