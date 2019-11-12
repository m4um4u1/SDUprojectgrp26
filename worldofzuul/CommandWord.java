package worldofzuul;

public enum CommandWord {
    GO("gå"), QUIT("afslut"), HELP("hjælp"), UNKNOWN("?"), INVENTORY("rygsæk"), GRAB("tag"), DEPOSIT("smid"), INSPECT("undersøg");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
