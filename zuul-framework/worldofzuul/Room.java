package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room // laver en ny klasse ved navn room
{
    //2 atributter intialiseres
    private String description;
    private HashMap<String, Room> exits; //laver et HashMap af key datatypen String og value datatypen Room (referer til sig selv)
    // Hashmap of items in the room
    private ArrayList<PlaceableObject> placeableObjectsInRoom;
    // Quiz atribute initialising
    private Quiz quizInRoom;
    private Grid gridForRoom;

    public Room(String description, int y, int x)  //Constructor der bruger en string ved navn description som data input
    {
        this.gridForRoom = new Grid(y, x);
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, ArrayList<PlaceableObject> placeableObjectsInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
    {
        this.gridForRoom = new Grid(y, x);
        this.placeableObjectsInRoom = placeableObjectsInRoom;
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, Quiz quizInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
    {
        this.gridForRoom = new Grid(y, x);
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.quizInRoom = quizInRoom;
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, Quiz quizInRoom, ArrayList<PlaceableObject> placeableObjectsInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
    {
        this.gridForRoom = new Grid(y, x);
        this.placeableObjectsInRoom = placeableObjectsInRoom;
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.quizInRoom = quizInRoom;
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public void setExit(String direction, Room neighbor) //methode
    {
        this.exits.put(direction, neighbor);
    } //Hashmappets key Strings sættes til at være dirctein variablen og valuesne sættes til at være variablen neighbor

    public String getShortDescription() //En metode som retunerer atrubuten descriptinon fra oven over
    {
        return this.description;
    }

    public void contructGrid() {
        this.gridForRoom.addExits(exits);
        if (checkForObjectsInRoom()) {
            for (PlaceableObject object : this.placeableObjectsInRoom) {
                this.gridForRoom.addObject(object);
            }
        }
    }

    public String checkPlayerPosistion() {
        String txt = "";
        if (checkForObjectsInRoom()) {
                for (PlaceableObject playerInRoom : this.placeableObjectsInRoom) {
                    if (playerInRoom instanceof Player) {
                        for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                            if (placeableObject.getX() == playerInRoom.getX() & placeableObject.getY() == playerInRoom.getY()) {
                                if (placeableObject instanceof Information || placeableObject instanceof WindMillPart) {
                                    txt = "\nYou are standing on a(n) " + placeableObject.getItemName();
                                }
                        }
                    }
                }
            }
        }
        return txt;
    }

    public String getLongDescription() //methode der retunerer diskriptonen af ruene og fortlæler hvor udgangene er
    {
        return "You are " + this.description + "\n" + getExitString() + "." + /*printItemsInRoom() + */ printQuizInRoom() + this.gridForRoom.getGrid() + checkPlayerPosistion();
    }

    private boolean checkForObjectsInRoom(){
        if (!(this.placeableObjectsInRoom == null)) {
            if (!(this.placeableObjectsInRoom.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    private String getExitString() //methode
    {
        String returnString = "These are the possible exits:"; //variable ærkleres

        /*
        henter alle keys fra hashmappet og sikrer sig der ikke er nogen duplikationer af samme key
        der efter gemmer den det i variablem keys.
         */
        Set<String> keys = this.exits.keySet();
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
        return this.exits.get(direction); //retunerer valuen på den key som direction peger på
    }

    public ArrayList<PlaceableObject> getPlaceableObjectsInRoom() {
        return this.placeableObjectsInRoom;
    }

    //Relevant now that there is a grapical view instead??
//    private String printItemsInRoom() {
//        if (this.placeableObjectsInRoom == null) {
//            return "";
//        }
//        if (this.placeableObjectsInRoom.isEmpty()) {
//            return "";
//        } else {
//            String text = "\nYou can see the following item(s): ";
//            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
//                if (!(placeableObject instanceof Player)) {
//                    text += placeableObject.getItemName() + " ";
//                }
//            }
//            return text;
//        }
//    }

    private String printQuizInRoom() {
        if (this.quizInRoom == null) {
            return "";
        }
        if (this.quizInRoom.isCompletion()) {
            return "";
        } else {
            String text = "\nThere is a quiz about: " + this.quizInRoom.getDescription();
            return text;
        }
    }

    public void doQuizInRoom() {
        if (this.quizInRoom == null) {
            System.out.println("There is no quiz in this room.");
            return;
        }
        if (this.quizInRoom.isCompletion()) {
            System.out.println("The quiz in this room has already been completed.");
        } else {
            this.quizInRoom.getQuiz();
        }
        return;
    }

    public Quiz getQuizInRoom() {
        return this.quizInRoom;
    }

    public void removeObjectsInRoom(PlaceableObject placeableObject) {
        this.placeableObjectsInRoom.remove(placeableObject);
    }

    public void addObjectsInRoom(PlaceableObject placeableObject) {
        this.placeableObjectsInRoom.add(placeableObject);
    }

    public void collectItem(Command command, Inventory playerInventory) {
        if (!(command.hasSecondWord())) {
            System.out.println("Which item would you like to collect?");
            return;
        } else {
            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                if (placeableObject.getItemName().toUpperCase().equals(command.getSecondWord().toUpperCase())) {
                    playerInventory.addItem(placeableObject);
                    removeObjectsInRoom(placeableObject);
                    System.out.println("You have collected: " + placeableObject.getItemName()); //+ printItemsInRoom());
                    return;
                }
            }
        }
        System.out.println("That item doesn't exist in this room");
    }
}

