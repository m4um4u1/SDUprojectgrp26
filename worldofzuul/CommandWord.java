package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"), GRAB("grab"), DEPOSIT("deposit"), INSPECT("inspect");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
