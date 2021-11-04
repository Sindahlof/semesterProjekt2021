package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room // laver en ny klasse ved navn room
{
    //2 atributter intialiseres
    private String description;
    private HashMap<String, Room> exits; //laver et HashMap af key datatypen String og value datatypen Room (referer til sig selv)
    private HashMap<String, Posistion> doorLocationsInRoom;
    // Hashmap of items in the room
    private ArrayList<PlaceableObject> placeableObjectsInRoom;
    // Quiz atribute initialising
    private Quiz quizInRoom;
    private String[][] grid;
    private int y, x;

    public Room(String description, int y, int x)  //Constructor der bruger en string ved navn description som data input
    {
        this.y = y;
        this.x = x;
        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
        doorLocationsInRoom = new HashMap<String, Posistion>();
    }
//
//    public Room(String description, ArrayList<PlaceableObject> placeableObjectsInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
//    {
//        this.y = y;
//        this.x = x;
//        this.placeableObjectsInRoom = placeableObjectsInRoom;
//        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
//        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
//        doorLocationsInRoom = new HashMap<String, Posistion>();
//
//    }
//
//    public Room(String description, Quiz quizInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
//    {
//        this.y = y;
//        this.x = x;
//        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
//        this.quizInRoom = quizInRoom;
//        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
//        doorLocationsInRoom = new HashMap<String, Posistion>();
//
//    }
//
//    public Room(String description, Quiz quizInRoom, ArrayList<PlaceableObject> placeableObjectsInRoom, int y, int x)  //Constructor der bruger en string ved navn description som data input
//    {
//        this.y = y;
//        this.x = x;
//        this.placeableObjectsInRoom = placeableObjectsInRoom;
//        this.description = description; //descripiton attrubuten sættes til at være det samme som constructor inpute
//        this.quizInRoom = quizInRoom;
//        this.exits = new HashMap<String, Room>(); //Hash mappet fra oven over intialiserers
//        doorLocationsInRoom = new HashMap<String, Posistion>();
//
//    }

    public void setExit(String direction, Room neighbor) //methode
    {
        this.exits.put(direction, neighbor);
    } //Hashmappets key Strings sættes til at være dirctein variablen og valuesne sættes til at være variablen neighbor

    public String getShortDescription() //En metode som retunerer atrubuten descriptinon fra oven over
    {
        return this.description;
    }

    //Metod to place symbols representing different objects into the multidimensional array called grid.
    private void constructGrid() {
        this.grid = new String[this.y][this.x];
        //For loop to add exits to the grid, It also makes a new Hashmap which saved the position where each exit was placed
        for (String exits : exits.keySet()) {
            if (exits.equals("north")) {
                Posistion posistion = new Posistion(0, Math.round(this.grid[0].length / 2));
                this.doorLocationsInRoom.put("north", posistion);
                this.grid[0][Math.round(this.grid[0].length / 2)] = "E";
            } else if (exits.equals("south")) {
                Posistion posistion = new Posistion(this.grid.length - 1, Math.round(this.grid[0].length / 2));
                this.doorLocationsInRoom.put("south", posistion);
                this.grid[this.grid.length - 1][Math.round(this.grid[0].length / 2)] = "E";
            } else if (exits.equals("west")) {
                Posistion posistion = new Posistion(Math.round(this.grid.length / 2), 0);
                this.doorLocationsInRoom.put("west", posistion);
                this.grid[Math.round(this.grid.length / 2)][0] = "E";
            } else if (exits.equals("east")) {
                Posistion posistion = new Posistion(Math.round(this.grid.length / 2), this.grid.length - 1);
                this.doorLocationsInRoom.put("east", posistion);
                this.grid[Math.round(this.grid.length / 2)][this.grid.length - 1] = "E";

            }
        }

        //For loop that adds the symbols representing the different objects that might be in the room
        if (checkForObjectsInRoom()) {
            for (PlaceableObject object : this.placeableObjectsInRoom) {
                if (object instanceof Information) {
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "A";
                } else if (object instanceof WindMillPart) {
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "W";
                } else {
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "P";
                }
            }
        }
    }

    //Method used for printing the grid basically going through the multidimensional String array
    public String printGrid() {
        constructGrid();
        String print = "";
        for (int y = 0; y < this.grid.length; y++) {
            if (y == 0) {
                print += "\n" + verticalLine() + "\n";
            }
            for (int x = 0; x < this.grid[y].length; x++) {
                if (x == 0) {
                    print += "|";
                }
                if (this.grid[y][x] == null) {
                    print += " " + " " + " |";
                } else {
                    print += " " + this.grid[y][x] + " |";
                }
            }
            print += "\n" + verticalLine() + "\n";
        }
        print += checkPlayerPosition();
        return print;
    }

    //Method used for making the vertical lines that separate each "vertical line" in the array
    private String verticalLine() {
        String line = "";
        for (int i = 0; i < this.grid[0].length; i++) {
            line += "----";
        }
        return line + "-";
    }

    // Metod to move the player
    public Player movePlayer(Player player, Command command) {
        if (!(command.hasSecondWord())) { //First of all check if we have given a direction to move in
            System.out.println("Move player where?");
        } else {
            // if true then checks which direction
            if (command.getSecondWord().equalsIgnoreCase("up")) {
                //Checks whether the player can move in the wanted direction. Which it does by checking if the player standing within the allowed area.
                if (player.getPosistion().getY() < this.grid.length && player.getPosistion().getY() > 0) {
                    player.getPosistion().setY(player.getPosistion().getY() - 1);
                    //Since we changed the player's location we'll need to update the Copy of player object that is in the array with object in the room
                    for (PlaceableObject object : this.placeableObjectsInRoom) {
                        if (object instanceof Player) {
                            this.placeableObjectsInRoom.remove(object);
                            this.placeableObjectsInRoom.add(player);
                            System.out.println(printGrid());
                        }
                    }
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("down")) {
                if (player.getPosistion().getY() < this.grid.length - 1 && player.getPosistion().getY() >= 0) {
                    player.getPosistion().setY(player.getPosistion().getY() + 1);
                    for (PlaceableObject object : this.placeableObjectsInRoom) {
                        if (object instanceof Player) {
                            this.placeableObjectsInRoom.remove(object);
                            this.placeableObjectsInRoom.add(player);
                            System.out.println(printGrid());
                        }
                    }
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("right")) {
                if (player.getPosistion().getX() < this.grid[0].length - 1 && player.getPosistion().getX() >= 0) {
                    player.getPosistion().setX(player.getPosistion().getX() + 1);
                    for (PlaceableObject object : this.placeableObjectsInRoom) {
                        if (object instanceof Player) {
                            this.placeableObjectsInRoom.remove(object);
                            this.placeableObjectsInRoom.add(player);
                            System.out.println(printGrid());
                        }
                    }
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("left")) {
                if (player.getPosistion().getX() < this.grid[0].length && player.getPosistion().getX() > 0) {
                    player.getPosistion().setX(player.getPosistion().getX() - 1);
                    for (PlaceableObject object : this.placeableObjectsInRoom) {
                        if (object instanceof Player) {
                            this.placeableObjectsInRoom.remove(object);
                            this.placeableObjectsInRoom.add(player);
                            System.out.println(printGrid());
                        }
                    }
                } else {
                    System.out.println("You cannot move there");
                }
            } else {
                System.out.println("Unknown direction");
            }
        }
        return player;
    }

    public String checkPlayerPosition() {
        String txt = "";
        if (checkForObjectsInRoom()) {
            for (PlaceableObject playerInRoom : this.placeableObjectsInRoom) {
                if (playerInRoom instanceof Player) {
                    for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                        if (placeableObject.getPosistion().getX() == playerInRoom.getPosistion().getX() && placeableObject.getPosistion().getY() == playerInRoom.getPosistion().getY()) {
                            if (placeableObject instanceof Information || placeableObject instanceof WindMillPart) {
                                txt = "\nYou are standing on a(n) " + placeableObject.getObjectName();
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
        return "You are " + this.description + "\n" + getExitString() + "." + /*printItemsInRoom() + */ printQuizInRoom() + printGrid();
    }

    private boolean checkForObjectsInRoom() {
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
        this.placeableObjectsInRoom.trimToSize();
    }

    //Method for adding a SINGLE object to the room!
    public void addObjectsInRoom(PlaceableObject placeableObject) {
        this.placeableObjectsInRoom.add(placeableObject);
        this.placeableObjectsInRoom.trimToSize();
    }

    //Method for adding an ArrayList of objects to the room!!
    public void addObjectsInRoom(ArrayList<PlaceableObject> placeableObjectsInRoom) {
        this.placeableObjectsInRoom = placeableObjectsInRoom;
    }

    public void addQuizInRoom(Quiz quizInRoom) {
        this.quizInRoom = quizInRoom;
    }

    public void collectItem(Command command, Inventory playerInventory) {
        if (!(command.hasSecondWord())) {
            System.out.println("Which item would you like to collect?");
            return;
        } else {
            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                if (placeableObject.getObjectName().equalsIgnoreCase(command.getSecondWord())) {
                    if (checkPlayerPosition().equals("")) {
                        System.out.println("You are not close enough to that item");
                        return;
                    }
                    playerInventory.addItem(placeableObject);
                    removeObjectsInRoom(placeableObject);
                    System.out.println("You have collected: " + placeableObject.getObjectName()); //+ printItemsInRoom());
                    return;
                }
            }
        }
        System.out.println("That item doesn't exist in this room");
    }

    public String[][] getGrid() {
        return this.grid;
    }
}

