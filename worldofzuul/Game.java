package worldofzuul;

import java.util.ArrayList;
import Trash.*;

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
        entre.setTrash(new TrashPlastic(1,"Banan","en banan der blev spist"));
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
        else if (commandWord == CommandWord.INSPECT) {
            inspectTrash(command);
        }
        return wantToQuit;
    }
    
    //Method to print the inventory - prints the String as well as the trash held
    private void printInventory() {
        String output = "";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getName() + " ";
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
        String output = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(trash)) {
                output = inventory.get(i).getDescription() + " ";
            }
        }
        System.out.println(output);
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
