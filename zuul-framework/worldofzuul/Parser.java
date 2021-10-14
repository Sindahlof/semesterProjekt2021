package worldofzuul;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private CommandWords commands; //attribut
    private Scanner reader; //attribut

    public Parser() // der bliver lavet to objekter commands og reader
    {
        commands = new CommandWords();
        reader = new Scanner(System.in); //skaber bruger input *Atribut
    }

    public Command getCommand() //metode, der laver tre inputlines, den første String er lig med null, String word1, word2 erklæres til null
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // der printes mindre end-tegn

        inputLine = reader.nextLine(); //en metode, hvor der læses, hvad der står på reader. Den returnerer, hvad der står på reader til inputLine

        Scanner tokenizer = new Scanner(inputLine); //skaber et objekt, som er et scanner input, der deler input op i Strings
        if(tokenizer.hasNext()) { //tjekker, om der er en String, den kan scannes efterfølgende
            word1 = tokenizer.next(); // word1 bliver fjernet fra Scanneren og peger på den String
            if(tokenizer.hasNext()) { // tjekker, om der er et mellemrum efter word1
                word2 = tokenizer.next(); //hvis der er et mellemrum efter word1, bliver word2 fjernet fra Scanneren og peger på den String
            }
        }

        return new Command(commands.getCommandWord(word1), word2); //den returner et nyt objekt Command, metode kører på word1,
        // men returnerer også word2
    }

    public void showCommands() //metode, der kalder på showAll-metoden
    {
        commands.showAll();
    }
}
