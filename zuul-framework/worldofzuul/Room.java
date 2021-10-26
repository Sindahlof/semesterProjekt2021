package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room // laver en ny klasse ved navn room

{
    //2 atributter intialiseres
    private String description;
    private HashMap<String, Room> exits; //laver et HashMap af key datatypen String og value datatypen Room (referer til sig selv)
    // Hashmap of items in the room
    private ArrayList<Item> itemsInRoom;
    // Quiz atribute initialising
    private Quiz quizInRoom;

    public Room(String description)  //Constructor der bruger en string ved navn description som data input
    {
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public void setExit(String direction, Room neighbor) //methode
    {
        exits.put(direction, neighbor);
    } //Hashmappets key Strings sættes til at være dirctein variablen og valuesne sættes til at være variablen neighbor

    public String getShortDescription() //En metode som retunerer atrubuten descriptinon fra oven over
    {
        return description;
    }

    public String getLongDescription() //methode der retunerer diskriptonen af ruene og fortlæler hvor udgangene er
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString() //methode
    {
        String returnString = "Exits:"; //variable ærkleres

        /*
        henter alle keys fra hashmappet og sikrer sig der ikke er nogen duplikationer af samme key
        der efter gemmer den det i variablem keys.
         */
        Set<String> keys = exits.keySet();
        /*
        for each loop der itterer over "arrayet" keys og sætter mellemrum og exits ind sammne med det der
        står på retunrString
         */

        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString; //retuner returnString
    }

    public Room getExit(String direction) //methode med return datatypen Room
    {
        return exits.get(direction); //retunerer valuen på den key som direction peger på
    }
}

