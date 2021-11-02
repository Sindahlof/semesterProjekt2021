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
    private String[][] grid;

    public Room(String description)  //Constructor der bruger en string ved navn description som data input
    {
        this.grid = new String[3][3];
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, ArrayList<PlaceableObject> placeableObjectsInRoom)  //Constructor der bruger en string ved navn description som data input
    {
        this.grid = new String[3][3];
        this.placeableObjectsInRoom = placeableObjectsInRoom;
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, Quiz quizInRoom)  //Constructor der bruger en string ved navn description som data input
    {
        this.grid = new String[3][3];
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.quizInRoom = quizInRoom;
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    public Room(String description, Quiz quizInRoom, ArrayList<PlaceableObject> placeableObjectsInRoom)  //Constructor der bruger en string ved navn description som data input
    {
        this.grid = new String[3][3];
        this.placeableObjectsInRoom = placeableObjectsInRoom;
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.quizInRoom = quizInRoom;
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
    }

    private void setPlaceableObjectLocation(){
        for (PlaceableObject placeableObject : placeableObjectsInRoom){
            int x = numberBetween1n3();
            int y = numberBetween1n3();

            if (placeableObject instanceof Information){
                this.grid[x][y] = "I";
            }else if (placeableObject instanceof WindMillPart){
                this.grid[x][y] = "W";
            }
        }
    }

    public void setExit(String direction, Room neighbor) //methode
    {
        this.exits.put(direction, neighbor);
    } //Hashmappets key Strings sættes til at være dirctein variablen og valuesne sættes til at være variablen neighbor

    public String getShortDescription() //En metode som retunerer atrubuten descriptinon fra oven over
    {
        return this.description;
    }

    public String getLongDescription() //methode der retunerer diskriptonen af ruene og fortlæler hvor udgangene er
    {
        return "You are " + this.description + "\n" + getExitString() + "." + printItemsInRoom() + printQuizInRoom();
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

    private String printItemsInRoom() {
        if (this.placeableObjectsInRoom == null) {
            return "";
        }
        if (this.placeableObjectsInRoom.isEmpty()) {
            return "";
        } else {
            String text = "\nYou can see the following item(s): ";
            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                text += placeableObject.getItemName() + " ";
            }
            return text;
        }
    }

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
    private String printRoomLayout(){
        String roomLayout = "";

        roomLayout = "________________________\n";

        for (int y = 0; y<grid.length; y++){

            for (int x = 0; x<grid[y].length; x++){

            }
        }

        return roomLayout;
    }

    public Quiz getQuizInRoom() {
        return this.quizInRoom;
    }

    public void removeItemsInRoom(PlaceableObject placeableObject) {
        this.placeableObjectsInRoom.remove(placeableObject);
    }

    public void collectItem(Command command, Inventory playerInventory) {
        if (!(command.hasSecondWord())) {
            System.out.println("Which item would you like to collect?");
            return;
        } else {
            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                if (placeableObject.getItemName().toUpperCase().equals(command.getSecondWord().toUpperCase())) {
                    playerInventory.addItem(placeableObject);
                    removeItemsInRoom(placeableObject);
                    System.out.println("You have collected: " + placeableObject.getItemName() + printItemsInRoom());
                    return;
                }
            }
        }
        System.out.println("That item doesn't exist in this room");
    }

    private int numberBetween1n3(){
        int range = (3-1)*1;
        return (int)(Math.random()*range)+1;
    }
}

