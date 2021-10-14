/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package worldofzuul;

public class Command
{
    private CommandWord commandWord; //attribut
    private String secondWord; //attribut

    public Command(CommandWord commandWord, String secondWord) //constructor initialiserer commandWord eller secondWord
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord() //metode returnerer commandWord
    {
        return commandWord;
    }

    public String getSecondWord() //metode returnerer secondWord
    {
        return secondWord;
    }

    public boolean isUnknown() //metode returnerer, om commandWord er det samme som "?" (CommandWord.UNKNOWN)
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    public boolean hasSecondWord() //metode returnerer, om secondWord ikke er lige med null
    {
        return (secondWord != null);
    }
}

