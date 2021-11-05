package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room // laver en ny klasse ved navn room
{
    //2 atributter intialiseres
    private final String description;
    private final HashMap<String, Room> exits; //laver et HashMap af key datatypen String og value datatypen Room (referer til sig selv)
    private final HashMap<String, Position> doorLocationsInRoom;

    private ArrayList<PlaceableObject> placeableObjectsInRoom;

    private Quiz quizInRoom;
    private String[][] grid;
    private final int y;
    private final int x;

    public Room(String description, int y, int x) { //Constructor der bruger en string ved navn description som data input
        this.y = y;
        this.x = x;
        this.description = description; //descripiton attributen sættes til at være det samme som constructor inpute
        this.exits = new HashMap<>(); //Hash mappet fra oven over intialiserers
        this.doorLocationsInRoom = new HashMap<>();
        this.placeableObjectsInRoom = new ArrayList<>();
    }

    //Method to place symbols representing different objects into the multidimensional array called grid.
    public void constructGrid() {
        this.grid = new String[this.y][this.x];
        //For loop to add exits to the grid, It also adds their position to a new Hashmap.
        for (String directions : this.exits.keySet()) {
            switch (directions) {
                case "north": {
                    Position position = new Position(0, Math.round(this.grid[0].length / 2));
                    this.grid[position.getY()][position.getX()] = "E";
                    this.doorLocationsInRoom.put("north", position);
                    break;
                }
                case "south": {
                    Position position = new Position(this.grid.length - 1, Math.round(this.grid[0].length / 2));
                    this.grid[position.getY()][position.getX()] = "E";
                    this.doorLocationsInRoom.put("south", position);
                    break;
                }
                case "west": {
                    Position position = new Position(Math.round(this.grid.length / 2), 0);
                    this.doorLocationsInRoom.put("west", position);
                    this.grid[position.getY()][position.getX()] = "E";
                    break;
                }
                case "east": {
                    Position position = new Position(Math.round(this.grid.length / 2), this.grid[0].length - 1);
                    this.doorLocationsInRoom.put("east", position);
                    this.grid[position.getY()][position.getX()] = "E";
                    break;
                }
                default: {
                    break;
                }
            }
        }

        //For loop that adds the symbols representing the different objects that might be in the room
        if (checkForObjectsInRoom()) {
            for (PlaceableObject object : this.placeableObjectsInRoom) {
                if (object instanceof Information) {
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "A";
                } else if (object instanceof WindMillPart) {
                    if (!(this.quizInRoom ==null)){
                        if (this.quizInRoom.isCompletion()) {
                            this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "W";
                        }
                    }else{
                        this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "W";
                    }

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
            if (checkForObjectsInRoom()) {
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
                                System.out.println(printGrid() + checkPlayerPosition());
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
                                System.out.println(printGrid() + checkPlayerPosition());
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
                                System.out.println(printGrid() + checkPlayerPosition());
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
                                System.out.println(printGrid() + checkPlayerPosition());
                            }
                        }
                    } else {
                        System.out.println("You cannot move there");
                    }
                } else {
                    System.out.println("Unknown direction");
                }
            }
        }
        return player;
    }

    public String checkPlayerPosition() {
        String txt = "";
        if (checkForObjectsInRoom()) {
            for (PlaceableObject playerInRoom : this.placeableObjectsInRoom) {
                if (playerInRoom instanceof Player) {
                    if (!(atWhichExit((Player) playerInRoom) == null)) {
                        txt = "\nYou are standing at the " + atWhichExit((Player) playerInRoom) + " exit";
                        break;
                    }
                    for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                        if (placeableObject.getPosistion().equals(playerInRoom.getPosistion())) {
                            if (placeableObject instanceof Information) {
                                txt = "\nYou are standing on a(n) " + placeableObject.getObjectName();
                                break;
                            }
                            if (placeableObject instanceof WindMillPart){
                                if (!(this.quizInRoom == null)){
                                    if (this.quizInRoom.isCompletion()){
                                        txt = "\nYou are standing on a " + placeableObject.getObjectName();
                                        break;
                                    }
                                }else{
                                    txt = "\nYou are standing on a " + placeableObject.getObjectName();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return txt;
    }

    private boolean checkForObjectsInRoom() {
        if (!(this.placeableObjectsInRoom == null)) {
            return !(this.placeableObjectsInRoom.isEmpty());
        }
        return false;
    }

    public String getLongDescription() {//methode der retunerer diskriptonen af ruene og fortlæler hvor udgangene er{
        return "You are " + this.description + "\n" + getExitString() + "." + /*printItemsInRoom() + */ printQuizInRoom() + printGrid() + checkPlayerPosition();
    }

    public void setExit(String direction, Room neighbor) {
        this.exits.put(direction, neighbor);
        constructGrid();
    } //Hashmappets key Strings sættes til at være dirctein variablen og valuesne sættes til at være variablen neighbor

    public String atWhichExit(Player player) {
        for (String direction : this.doorLocationsInRoom.keySet()) {
            switch (direction) {
                case "north":
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "north";
                    }
                case "south":
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "south";
                    }
                case "east":
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "east";
                    }
                case "west":
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "west";
                    }
            }
        }
        return null;
    }

    private String getExitString() {//methode
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

    public Room getExit(String direction) {//methode med return datatypen Room
        return this.exits.get(direction); //retunerer valuen på den key som direction peger på
    }

    public Position getExitPosition(String direction) {
        switch (direction) {
            case "south":
                return this.doorLocationsInRoom.get("north");
            case "north":
                return this.doorLocationsInRoom.get("south");
            case "east":
                return this.doorLocationsInRoom.get("west");
            case "west":
                return this.doorLocationsInRoom.get("east");
        }
        return this.doorLocationsInRoom.get(direction);
    }

    private String printQuizInRoom() {
        if (this.quizInRoom == null) {
            return "";
        }
        if (this.quizInRoom.isCompletion()) {
            return "";
        } else {
            return "\nThere is a quiz about: " + this.quizInRoom.getDescription();
        }
    }

    public Player doQuizInRoom(Player player) {
        if (this.quizInRoom == null) {
            System.out.println("There is no quiz in this room.");
            return player;
        }
        if (this.quizInRoom.isCompletion()) {
            System.out.println("The quiz in this room has already been completed.");
            return player;
        }
        this.quizInRoom.getQuiz(player);
        return player;
    }

    public Quiz getQuizInRoom() {
        return this.quizInRoom;
    }

    public void addQuizToRoom(Quiz quizInRoom) {
        this.quizInRoom = quizInRoom;
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

    public void collectObject(Command command, Inventory playerInventory) {
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
}

