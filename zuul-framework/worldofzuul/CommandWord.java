package worldofzuul;

public enum CommandWord {
    EXIT("exit"), QUIT("quit"), HELP("help"), COLLECT("collect"),
    INSPECT("inspect"),INVENTORY("inventory"),UNKNOWN("?"),
    DoQUIZ("do-quiz"),ASSEMBLE("assemble"),MOVE("move");


    private String commandString;

    CommandWord(String commandString) //constructor der intialiserer atributterne/methoder ovenover
    {
        this.commandString = commandString;
    }

    @Override
    public String toString() //methode retunerer commandString, toString methoden overrides her
    {
        return this.commandString;
    }
}
