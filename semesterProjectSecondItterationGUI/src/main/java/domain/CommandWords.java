package domain;

import java.util.HashMap;


public class CommandWords {
    private HashMap<String, CommandWord> validCommands; //atribut af data typen HashMap og med variable navnet vaildCoammands

    public CommandWords() //consrtuctor, intialsierer vailCommands som et hashmap
    {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) { //et for each loop der traveserer HashMap'et

            /*
            if statement der chekker om inputet fra brugeren ikke er det samme som UNKNOWN fra CommandWord enum'et
            vil programmet bruge toString methoden og retunerer stringen så programmet chekker hvilken commando
            du har inputet
             */
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) //mehtode der bruger datat typen CommandWord og tager af input som Strings
    {
        /*
        Varible der gør brug af .get methoden fra java, denne variable bruger data typen CommandWord
        den retunerer hvad der står i HashMap'ets bestemte plads
         */
        CommandWord command = validCommands.get(commandWord);
        if (command != null) { //if statement der sørge for at brugeren har skrevet noget
            return command;
        } else {
            return CommandWord.UNKNOWN; //else retunerer unknown hvis der ikke står noget på input scanner
        }
    }

    public boolean isCommand(String aString) //methode der chekker om en bestemt key findes i hashmappet
    {
        return validCommands.containsKey(aString);
    }

    public String showAll()  //methode der ikke retuner noget
    {
        String commands;

        /*
        //for each loop, der løber gennem alle keys, og printer dem ud.
         */

        for (String command : validCommands.keySet()) {
            switch (command) { //To get a more specific description for each command
                case "move":
                    commands = "Type \"" + command + "\" to move around inside a room";
                    return commands;

                case "exit":
                    commands = "Type \"" + command + "\" to exit the room";
                    return commands;


                case "quit":
                    commands = "Type \"" + command + "\" to quit the game";
                    return commands;


                case "collect":
                    commands = "Type \"" + command + "\" to collect an object";
                    return commands;


                case "inventory":
                    commands = "Type \"" + command + "\" to view your inventory";
                    return commands;


                case "inspect":
                    commands = "Type \"" + command + "\" to inspect an item in your inventory";
                    return commands;


                case "help":
                    commands = "Type \"" + command + "\" to get this menu";
                    return commands;


                case "do-quiz":
                    commands = "Type \"" + command + "\" to start the quiz in a room";
                    return commands;


                case "assemble":
                    commands = "Type \"" + command + "\" to assemble the windmill";
                    return commands;


                case "health":
                    commands = "Type \"" + command + "\" to get the player health";
                    return commands;

            }
        }
        return "";
    }
}
