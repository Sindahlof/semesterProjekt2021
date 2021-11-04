package worldofzuul;

import java.util.ArrayList;
import java.util.Locale;
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
        this.dead=false;
        this.parser = new Parser(); //attributten parser sættes til at være klassen Parser
        this.playerInventory = new Inventory(); //Initializer player's inventory
    }

    private void createRooms() {//en metode til at lave rum
        Room outside, theatre, pub, lab, office, getRekt;
        String[] answers1 = {"A. Fordi ingen kan lide ham", "B. Fordi kage ", "C. Fordi all elsker ham"};

        Quiz quiz1 = new Quiz("Hvorfor er Sindahl adoptered", answers1, "C", "Sindahl");

        PlaceableObject placeableObject1 = new Information("Article", """
                \sThis Artical is about Sindahl.
                Sindahl is a student at SDU and studying Software Engineering.
                Some would argue that he is even good at it. :)\s""", 1, 2);
        PlaceableObject placeableObject2 = new WindMillPart("Windmill-Wing", 4, "This is one of the windmill wings", 2, 1);

        placeableObject1.getPosistion().updatePosition(1, 2);

        ArrayList<PlaceableObject> itemsInOutside = new ArrayList<>();
        itemsInOutside.add(placeableObject1);
        itemsInOutside.add(placeableObject2);

        this.player1 = new Player("Player 1", 3, 2);


        outside = new Room("outside the main entrance of the university", 5, 5);
        theatre = new Room("in a lecture theatre", 3, 3);
        pub = new Room("in the campus pub", 3, 10);
        lab = new Room("in a computing lab", 5, 5);
        office = new Room("in the computing admin office", 3, 3);
        getRekt = new Room("Imagine actually moving all the way here",1,1);
        this.assembleRoom = new Room("in the room where you assemble your windmill", 3, 3);

        outside.setExit("north", this.assembleRoom);
        outside.setExit("south", lab);
        outside.setExit("east", theatre);
        outside.setExit("west", pub);
        outside.addObjectsInRoom(itemsInOutside);

        this.assembleRoom.setExit("south", outside);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("west", getRekt);

        getRekt.setExit("east",pub);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addQuizToRoom(quiz1);

        office.setExit("west", lab);

        this.currentRoom = outside;
        outside.addObjectsInRoom(this.player1);
    }

    public void play() {//metode, der sætter exit-conditionen

        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");

        System.out.print("Please select your difficulty: \n>");

        boolean difficultySelected = true;

        while(difficultySelected){
            Scanner input = new Scanner(System.in);
            String answer = input.next().toLowerCase();
            switch (answer){
                case "easy":
                    System.out.println("You have selected the easy difficulty, you have 10 health.");
                    this.player1.setHealth(10);
                    difficultySelected = false;
                    break;
                case "medium":
                    System.out.println("You have selected the medium difficulty, you have 5 health.");
                    this.player1.setHealth(5);
                    difficultySelected = false;
                    break;
                case "hard":
                    System.out.println("You have selected the hard difficulty, you have 2 health.");
                    this.player1.setHealth(2);
                    difficultySelected = false;
                    break;
                default:
                    System.out.println("Unknown difficulty, try again. \n>");
            }
        }

        printWelcome();

        boolean finished = false;

        while (!finished) {
            Command command = this.parser.getCommand();
            finished = processCommand(command);
        }
        if (this.gameCompleted) {
            System.out.println("Congratulations you have successfully completed the game and therefore you are smarter than Sindahl.");
        }
        if (this.dead){
            System.out.println("You have lost all your health and therefore died.");
        }

        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("P = Player, E = Exit, A = Article, W = Windmill-part");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(this.currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command){ //den tjekker konsol-inputsene og tjekker, om de passer med dem i enum'et
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        //Switch case for processing the command.
        switch (commandWord) {
            case HELP:
                this.printHelp();
                break;
            case EXIT:
                this.exitRoom();
                break;
            case MOVE:
                this.player1 = this.currentRoom.movePlayer(this.player1, command);
                break;
            case QUIT:
                wantToQuit = this.quit(command);
                break;
            case DoQUIZ:
                this.player1 = this.currentRoom.doQuizInRoom(this.player1);
                if (this.player1.getHealth()==0){
                    this.dead = true;
                    return true;
                }
                break;
            case COLLECT:
                this.currentRoom.collectObject(command, this.playerInventory);
                break;
            case INSPECT:
                this.playerInventory.inspectItem(command);
                break;
            case ASSEMBLE:
                if (successfulAssemble()) {
                    this.gameCompleted = true;
                    return true;
                }
                break;
            case INVENTORY:
                this.playerInventory.printInventory();
                return false;
            default:
                System.out.println("I don't know what you mean...");
                return false;
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        this.parser.showCommands();
    }

    private void exitRoom() {
        String direction = this.currentRoom.atWhichExit(this.player1);
        if (direction== null){
            System.out.println("You are not at an exit");
            return;
        }

        Room nextRoom = this.currentRoom.getExit(direction); //henter det rum, der er i den givne direction

        if (nextRoom == null) { //tjekker, om der er rum i den givne direction
            System.out.println("There is no door!");
        } else {
            this.currentRoom.removeObjectsInRoom(this.player1);
            this.currentRoom = nextRoom;
            this.player1.getPosistion().updatePosition(this.currentRoom.getExitPosition(direction));
            this.currentRoom.addObjectsInRoom(this.player1);
            System.out.println(this.currentRoom.getLongDescription());
        }
    }

    private boolean successfulAssemble() {
        if (this.currentRoom == this.assembleRoom) {
            if (this.playerInventory.collectedAllWindmillParts()) {
                return true;
            } else {
                System.out.println("You have not collected all windmill-parts");
            }
        } else {
            System.out.println("You are in the wrong room head to the assemble room");
        }
        return false;
    }

    private boolean quit(Command command){  //metode til at stoppe spillet
        if (command.hasSecondWord()) { //tjekker, om der er et ord efter.
            System.out.println("Quit what?");
            return false;
        } else { //returnerer "true", som stopper spillet via de andre metoder
            return true;
        }
    }
}
