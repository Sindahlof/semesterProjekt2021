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

    // y og x are placeholders for the grids dimensions
    private final int y;
    private final int x;

    public Room(String description, int y, int x) {
        this.y = y;
        this.x = x;
        this.description = description; //descripiton attributen sættes til at være det samme som constructor inpute
        this.exits = new HashMap<>(); //Hash mappet fra oven over intialiserers
        this.doorLocationsInRoom = new HashMap<>();
        this.placeableObjectsInRoom = new ArrayList<>();
    }

    //Method to place symbols representing different objects into the multidimensional array called grid.
    public void constructGrid() {
        this.grid = new String[this.y][this.x]; //Every time this method is used a new grid is made. This way I don't need to remove items from the grid when they are moved
        //For loop to add exits to the grid, It also adds their position to a new Hashmap.
        for (String directions : this.exits.keySet()) {
            switch (directions) {
                case "north": {
                    //First generates the position of the north exit
                    Position position = new Position(0, Math.round(this.grid[0].length / 2));
                    //Second places the exit symbol
                    this.grid[position.getY()][position.getX()] = "E";
                    //Lastly saved the position in new hashmap
                    this.doorLocationsInRoom.put("north", position);
                    break;
                }

                //Same analogy as north
                case "south": {
                    Position position = new Position(this.grid.length - 1, Math.round(this.grid[0].length / 2));
                    this.grid[position.getY()][position.getX()] = "E";
                    this.doorLocationsInRoom.put("south", position);
                    break;
                }

                //Same analogy as north
                case "west": {
                    Position position = new Position(Math.round(this.grid.length / 2), 0);
                    this.doorLocationsInRoom.put("west", position);
                    this.grid[position.getY()][position.getX()] = "E";
                    break;
                }

                //Same analogy as north
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
        if (checkForObjectsInRoom()) { //Method to check if there are any objects in the room
            for (PlaceableObject object : this.placeableObjectsInRoom) { //Goes through all the placeableObjectsInRoom
                if (object instanceof Information) {// If it's an information object then add an A to the grid
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "A";
                } else if (object instanceof WindMillPart) {//Checks if it's a windmill part
                    if (!(this.quizInRoom == null)) {//Checks if there is a quiz in the room
                        if (this.quizInRoom.isCompletion()) {//Checks if the quiz has been completed
                            this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "W";//Adds the windmill part symbol to the grid
                        }
                    } else {
                        this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "W"; // If there aren't any quiz in the room then it just adds the windmill part symbol to the grid
                    }

                } else {
                    this.grid[object.getPosistion().getY()][object.getPosistion().getX()] = "P"; //If none of the objects are either a windmill part or an information object then it must be the player so we add the player symbol to the grid
                }
            }
        }
    }

    //Method used for printing the grid basically going through the multidimensional String array
    public String printGrid() {
        constructGrid();
        String print = "";
        for (int y = 0; y < this.grid.length; y++) { // Does one vertical line at the time
            if (y == 0) {
                print += "\n" + verticalLine() + "\n";
            }
            for (int x = 0; x < this.grid[y].length; x++) { //Loop for placing each symbol in the horizontal line
                if (x == 0) { //Starts with placing the end border symbolized by |
                    print += "|";
                }
                if (this.grid[y][x] == null) { //If the spot in the grid is empty then add a (    |).
                    print += " " + " " + " |";
                } else {
                    print += " " + this.grid[y][x] + " |"; //If there is something in the grid then print that symbol e.q. (" P |")
                }
            }
            print += "\n" + verticalLine() + "\n"; // when it's done with one vertical line separate it by --------------
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

    // Method to move the player
    public Player movePlayer(Player player, Command command) {
        if (!(command.hasSecondWord())) { //First of all check if we have given a direction to move in
            System.out.println("Move player where?");
        } else {
            // if true then checks which direction
            if (command.getSecondWord().equalsIgnoreCase("up")) {
                //Checks whether the player can move in the wanted direction. Which it does by checking if the player is standing within the allowed area.
                if (player.getPosistion().getY() < this.grid.length && player.getPosistion().getY() > 0) {
                    this.placeableObjectsInRoom.remove(player); //First we remove the old player in the room
                    player.getPosistion().setY(player.getPosistion().getY() - 1);//Change the player position
                    this.placeableObjectsInRoom.add(player); //Add the player to the room again
                    System.out.println(printGrid() + checkPlayerPosition()); //Display the updated grid
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("down")) { // Same analogy as up
                if (player.getPosistion().getY() < this.grid.length - 1 && player.getPosistion().getY() >= 0) {
                    this.placeableObjectsInRoom.remove(player);
                    player.getPosistion().setY(player.getPosistion().getY() + 1);
                    this.placeableObjectsInRoom.add(player);
                    System.out.println(printGrid() + checkPlayerPosition());
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("right")) { // Same analogy as up
                if (player.getPosistion().getX() < this.grid[0].length - 1 && player.getPosistion().getX() >= 0) {
                    this.placeableObjectsInRoom.remove(player);
                    player.getPosistion().setX(player.getPosistion().getX() + 1);
                    this.placeableObjectsInRoom.add(player);
                    System.out.println(printGrid() + checkPlayerPosition());
                } else {
                    System.out.println("You cannot move there");
                }
            } else if (command.getSecondWord().equalsIgnoreCase("left")) { // Same analogy as up
                if (player.getPosistion().getX() < this.grid[0].length && player.getPosistion().getX() > 0) {
                    this.placeableObjectsInRoom.remove(player);
                    player.getPosistion().setX(player.getPosistion().getX() - 1);
                    this.placeableObjectsInRoom.add(player);
                    System.out.println(printGrid() + checkPlayerPosition());
                } else {
                    System.out.println("You cannot move there");
                }
            } else {
                System.out.println("Unknown direction");
            }
        }
        return player;
    }

    public String checkPlayerPosition() { //Method used to check whether the player is standing on an exit, information object or a windmill part.
        String txt = "";//Initializing the return txt
        if (checkForObjectsInRoom()) { //Checks if there are any objects in the room
            for (PlaceableObject playerInRoom : this.placeableObjectsInRoom) { //For loop to go through all the objects in the room.
                // Its purpose is basically to find the player object in the array
                if (playerInRoom instanceof Player) { // When it gets the player object we continue
                    if (!(atWhichExit((Player) playerInRoom) == null)) { //atWhichExit returns the exit the player is at.
                        // So !(atWhichExit(playerInRoom) == null) is true when the player is at an exit and false if the player is not at an exit
                        txt = "\nYou are standing at the " + atWhichExit((Player) playerInRoom) + " exit"; //sets the return txt to e.g. "you are standing at the north exit
                        break; //Breaks out of the massive if chain and straight to the return statement
                    }
                    //If the player isn't at an exit then it starts checking if the player is at standing on any other objects in the room
                    for (PlaceableObject placeableObject : this.placeableObjectsInRoom) { //So goes through all the objects in the room
                        if (placeableObject.getPosistion().equals(playerInRoom.getPosistion())) {
                            //If any of the objects position is equal to the players position we then check which time of object it is
                            if (placeableObject instanceof Information) {
                                //If it's an information item it sets the return string to "You are standing on a(n) + object name"
                                txt = "\nYou are standing on a(n) " + placeableObject.getObjectName();
                                break; //Breaks out of the massive if chain and straight to the return statement
                            }
                            if (placeableObject instanceof WindMillPart) {
                                //If it's a windmill part it then checks if there is a quiz in the room
                                if (!(this.quizInRoom == null)) { //!(this.quizInRoom == null) is true when there is a quiz and false when there isn't
                                    //if there is a quiz  it then starts to check if its completed
                                    if (this.quizInRoom.isCompletion()) {
                                        //if the quiz is completed then it'll let you see the object
                                        txt = "\nYou are standing on a " + placeableObject.getObjectName();
                                        break;
                                    }
                                } else {
                                    //If there are no quiz in the room it will let you see the object
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


    private boolean checkForObjectsInRoom() { //Method to check if there are objects in the room
        if (!(this.placeableObjectsInRoom == null)) {//Checks if the arraylist with objects has been initialized
            return !(this.placeableObjectsInRoom.isEmpty());// returns true if the arraylist is NOT empty and false if it IS empty
        }
        return false; //returns false if the object Arraylist hasn't been initialized
    }

    public String getLongDescription() {//Method which basically makes the output string for when you first enter a room
        return "You are " + this.description + "\n" + getExitString() + "." + /*printItemsInRoom() + */ printQuizInRoom() + printGrid() + checkPlayerPosition();
    }

    public void setExit(String direction, Room neighbor) {//Method to add exit to the hashmap, which takes strings as keys which point at a room values
        this.exits.put(direction, neighbor);
        constructGrid();
    }

    public String atWhichExit(Player player) { //Method used to find which exit the player is standing at
        for (String direction : this.doorLocationsInRoom.keySet()) {//Goes through all the keys that the hashmap doorlocationsinroom has
            switch (direction) {
                case "north": //If the hashmap has a key "north" it'll check if the player's position is the same as the value which the key is linked to.
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "north"; //returns the direction the exit is at for where the player is standing
                    }
                case "south": //same analogy as north
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "south";
                    }
                case "east": //same analogy as north
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "east";
                    }
                case "west": //same analogy as north
                    if (this.doorLocationsInRoom.get(direction).equals(player.getPosistion())) {
                        return "west";
                    }
            }
        }
        return null; //return null if the player is not standing at any exits
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


    public Position getExitPosition(String direction) {//Method used to get the position of an exit.
        // So if you go through the south door in one room, this method will return the position of the north door in the other room
        switch (direction) {
            case "south"://So if the given direction is south it will return the position of the north door
                return this.doorLocationsInRoom.get("north");
            case "north"://Same analogy as south
                return this.doorLocationsInRoom.get("south");
            case "east"://Same analogy as south
                return this.doorLocationsInRoom.get("west");
            case "west"://Same analogy as south
                return this.doorLocationsInRoom.get("east");
        }
        //in case the give direction is not south north east or west then it basically returns null (This never happens due to how the Method is used
        return this.doorLocationsInRoom.get(direction);
    }

    private String printQuizInRoom() {//Method used to inform the player that there is a quiz in the room.
        // Its only really used when you get the long description for a room
        if (this.quizInRoom == null) {//If there isn't any quiz then it returns an empty string
            return "";
        }
        if (this.quizInRoom.isCompletion()) {//If the quiz has already been completed then return an empty string
            return "";
        } else { //If there is a quiz and it hasn't been completed it'll tell the player "There is a quiz about: + the description"
            return "\nThere is a quiz about: " + this.quizInRoom.getDescription();
        }
    }

    public Player doQuizInRoom(Player player) { //This is a method that is used to start the quiz in a room - when you used the do-quiz command
        //It requires you to give it a player object so that if you answer the quiz wrong it can remove health from that player
        if (this.quizInRoom == null) { //it'll first check if there is a quiz in that room
            //if there isn't any quiz then it'll just print there is no quiz in the room. and return player
            System.out.println("There is no quiz in this room.");
            return player;
        }
        if (this.quizInRoom.isCompletion()) { //Checks if the quiz has already been completed
            //if it has been completed then it'll return the player object and print quiz completion
            System.out.println("The quiz in this room has already been completed.");
            return player;
        }
        //none of the above are true then it'll send the play to the method called getQuiz
        return this.quizInRoom.getQuiz(player);
    }

    public Quiz getQuizInRoom() { //Method to get the quiz in the room
        return this.quizInRoom;
    }

    public void addQuizToRoom(Quiz quizInRoom) { //Method used to add a quiz to a room.
        // This way we don't have to do it through the constructor which greatly reduces the amount of code
        this.quizInRoom = quizInRoom;
    }

    public void removeObjectsInRoom(PlaceableObject placeableObject) { //Method for removing objects from the room
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

    public void collectObject(Command command, Inventory playerInventory) {//Method used when you try to collect an item
        if (!(command.hasSecondWord())) { //First it checks if you have actually told what you want to collect
            System.out.println("Which object would you like to collect?");
            return;
        } else {
            for (PlaceableObject placeableObject : this.placeableObjectsInRoom) {
                //It now goes through all the objects in the room and sees if the object you are trying to collect is in the room
                if (placeableObject.getObjectName().equalsIgnoreCase(command.getSecondWord())) {
                    if (checkPlayerPosition().contains(placeableObject.getObjectName())) { //Checks if the player is standing on the object he is trying to collect
                        playerInventory.addItem(placeableObject);
                        removeObjectsInRoom(placeableObject);
                        System.out.println("You have collected: " + placeableObject.getObjectName());
                        return;
                    }
                    System.out.println("You are not close enough to that item");
                    return;
                }
            }
        }
        System.out.println("That item doesn't exist in this room");
    }
}

