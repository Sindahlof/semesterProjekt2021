package worldofzuul;

public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString) //constructor der intialiserer atributterne/methoder ovenover
    {
        this.commandString = commandString;
    }

    @Override
    public String toString() //methode retunerer commandString, toString methoden overrides her
    {
        return commandString;
    }
}
