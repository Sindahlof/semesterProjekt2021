package worldofzuul;

import java.util.Scanner;

public class Game //her "skabes" klassen Game
{
    //der laves 2 attributter, deres datatyper er taget fra andre klasser
    private Parser parser;
    private Room currentRoom;
    private final Inventory playerInventory;
    private boolean gameCompleted, dead;
    private Room assembleRoom;
    private Player player1;


    public Game() { //constructor Game defineres
        createRooms();
        this.gameCompleted = false;
        this.dead = false;
        this.parser = new Parser(); //attributten parser sættes til at være klassen Parser
        this.playerInventory = new Inventory(); //Initializer player's inventory
    }

    private void createRooms() {//en metode til at lave rum
        Room outside, theatre, pub, lab, office, getRekt;
        String[] answers1 = {"A. Fordi ingen kan lide ham", "B. Fordi kage ", "C. Fordi all elsker ham"};

        Quiz quiz1 = new Quiz("Hvorfor er Sindahl adoptered", answers1, "C", "Sindahl");
        Quiz quiz2 = new Quiz("Hvorfor er Sindahl adoptered", answers1, "A", "Sindahl");

        PlaceableObject placeableObject1 = new Information("Article", """
                \sThis Artical is about Sindahl.
                Sindahl is a student at SDU and studying Software Engineering.
                Some would argue that he is even good at it. :)\s""", 1, 2);
        PlaceableObject placeableObject2 = new WindMillPart("Windmill-Wing", 11, "This is one of the windmill wings", 2, 1);

        placeableObject1.getPosistion().updatePosition(1, 2);


        this.player1 = new Player("Player 1", 3, 2);


        outside = new Room("outside the main entrance of the university", 5, 5);
        theatre = new Room("in a lecture theatre", 3, 3);
        pub = new Room("in the campus pub", 3, 10);
        lab = new Room("in a computing lab", 5, 5);
        office = new Room("in the computing admin office", 3, 3);
        getRekt = new Room("Imagine actually moving all the way here", 1, 1);
        this.assembleRoom = new Room("in the room where you assemble your windmill", 3, 3);

        outside.setExit("north", this.assembleRoom);
        outside.setExit("south", lab);
        outside.setExit("east", theatre);
        outside.setExit("west", pub);

        outside.addObjectsInRoom(placeableObject1);
        outside.addObjectsInRoom(placeableObject2);
        outside.addQuizToRoom(quiz2);

        this.assembleRoom.setExit("south", outside);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("west", getRekt);

        getRekt.setExit("east", pub);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addObjectsInRoom(placeableObject2);
        lab.addQuizToRoom(quiz1);

        office.setExit("west", lab);

        this.currentRoom = outside;
        outside.addObjectsInRoom(this.player1);
    }

    public void play() {//Method which determines when the game is over

        System.out.println("Welcome to the World of Power!");
        System.out.println("World of Power is a game about the UN's 7th world goal; Affordable and clean energy");

        System.out.print("Please choose one of the following difficulties. EASY, NORMAL, HARD: \n>");

        boolean difficultySelected = false;

        //While loop for selecting difficulty
        while (!difficultySelected) {
            Scanner input = new Scanner(System.in);
            String answer = input.next().toLowerCase();
            switch (answer) {
                case "easy":
                    System.out.println("You have selected the easy difficulty, you have 10 health.");
                    this.player1.setHealth(10);
                    difficultySelected = true;
                    break;
                case "normal":
                    System.out.println("You have selected the normal difficulty, you have 5 health.");
                    this.player1.setHealth(5);
                    difficultySelected = true;
                    break;
                case "hard":
                    System.out.println("You have selected the hard difficulty, you have 2 health.");
                    this.player1.setHealth(2);
                    difficultySelected = true;
                    break;
                default:
                    System.out.print("Unknown difficulty, try again. \n>");
            }
        }

        printWelcome();

        boolean finished = false;

        while (!finished) {
            Command command = this.parser.getCommand();
            finished = processCommand(command);
        }

        //The game has 3 endings
        if (this.gameCompleted) { //1st ending the game is completed and you win
            System.out.println("Congratulations you have successfully collected all windmill parts and thereby completed the game!");
        }
        if (this.dead) { // 2nd ending the player is dead and you lose
            System.out.println("You have lost all your health and therefore died.");
        }
        // Last ending the player quits without completing or dying
        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Meaning of the following symbols:\n" +
                "P = Player\nE = Exit\nA = Article\nW = Windmill part");
        System.out.println("Type '" + CommandWord.HELP + " to get a list of commands");
        System.out.println();
        System.out.println(this.currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) { //den tjekker konsol-inputsene og tjekker, om de passer med dem i enum'et
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        //Switch case for processing the commands.
        switch (commandWord) {
            case HELP:
                this.printHelp();
                break;
            case EXIT: //command for exiting a room
                this.exitRoom();
                break;
            case MOVE: //command for moving around in a room
                this.player1 = this.currentRoom.movePlayer(this.player1, command);
                break;
            case QUIT:
                wantToQuit = this.quit(command);
                break;
            case DoQUIZ: //command for doing the quiz
                if (this.currentRoom.getQuizInRoom() == null) { //checks if there is a quiz in the room
                    System.out.println("There is no quiz in this room.");
                    break;
                }
                if (this.currentRoom.getQuizInRoom().isCompletion()) { //checks is the quiz is ALREADY completed
                    this.player1 = this.currentRoom.doQuizInRoom(this.player1);
                    break;
                }
                this.player1 = this.currentRoom.doQuizInRoom(this.player1);
                if (this.currentRoom.getQuizInRoom().isCompletion()) { //Checks if the player JUST completed the quiz
                    System.out.println(this.currentRoom.printGrid() + this.currentRoom.checkPlayerPosition());
                    System.out.println("Congratulations you have completed the quiz, a windmill part has been unlocked.");
                }
                if (this.player1.getHealth() == 0) { //Checks if the player has lost all his health
                    //if true changes boolean dead to true which triggers 3 ending;
                    this.dead = true;
                    wantToQuit = true;
                }
                break;
            case COLLECT:
                this.currentRoom.collectObject(this.playerInventory); //calling method for collecting an object in a room into an inventory
                break;
            case INSPECT:
                this.playerInventory.inspectObjects(command); //Calling a method to inspect an object in your inventory
                break;
            case ASSEMBLE:
                if (successfulAssemble()) { //Calling a method to assemble the windmill
                    this.gameCompleted = true; // This triggers the first end condition
                    return true;
                }
                break;
            case INVENTORY:
                this.playerInventory.printInventory(); //calling method to print player inventory
                break;
            case HEALTH:
                System.out.println("You have "+this.player1.getHealth()+" health left"); //prints the players health
                break;
            default:
                System.out.println("I don't know what you mean..."); //default case which basically means if you try to type a command that it doesn't know then this happens
                return false;
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("These are the possible commands in the game:");
        this.parser.showCommands();
    }

    private void exitRoom() { //Exit room method
        String direction = this.currentRoom.atWhichExit(this.player1); //First it gets which exit the player is at
        if (direction == null) { //If the player is not at any exits then it'll tell the player that
            System.out.println("You are not at an exit");
            return;
        }

        Room nextRoom = this.currentRoom.getExit(direction); //Gets the room which is linked to the exit the player is at

        if (nextRoom == null) { //Checks if there is a room at that exit location // this one is always false which makes it kind obsolete
            System.out.println("There is no door!");
        } else {
            this.currentRoom.removeObjectsInRoom(this.player1);            //Removes the player from the current room
            this.currentRoom = nextRoom;            //Changes to room to the next room
            this.player1.getPosistion().updatePosition(this.currentRoom.getExitPosition(direction)); //Updates the players position to match the exit in the next room.
            // (e.g. Goes through the north gate exits at the south gate in the next room)
            this.currentRoom.addObjectsInRoom(this.player1); //Adds the player object to the new room
            System.out.println(this.currentRoom.getLongDescription()); //Get the long description for the next room
        }
    }

    private boolean successfulAssemble() { //Method used for assembling the windmill
        if (this.currentRoom == this.assembleRoom) { //Checks if the player is in the correct room for assemble
            if (this.playerInventory.collectedAllWindmillParts()) {//Checks if the player has collected all the windmill parts
                return true;
            } else {
                System.out.println("You have not collected all windmill-parts");
            }
        } else {
            System.out.println("You are in the wrong room head to the assemble room");
        }
        return false;
    }

    private boolean quit(Command command) {  //metode til at stoppe spillet
        if (command.hasSecondWord()) { //tjekker, om der er et ord efter.
            System.out.println("Quit what?");
            return false;
        } else { //returnerer "true", som stopper spillet via de andre metoder
            return true;
        }
    }
}
