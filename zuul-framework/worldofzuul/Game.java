package worldofzuul;

import java.util.ArrayList;

public class Game //her "skabes" klassen Game
{
    //der laves 2 attributter, deres datatyper er taget fra andre klasser
    private Parser parser;
    private Room currentRoom;
    private final Inventory playerInventory;
    private boolean gameCompleted;
    private Room assembleRoom;
    private Player player1;


    public Game()  //constructoren Game defineres
    {
        createRooms();
        this.gameCompleted = false;
        this.parser = new Parser(); //attributten parser sættes til at være klassen Parser
        this.playerInventory = new Inventory(); //Initializer player's inventory

    }


    private void createRooms() //en metode til at lave rum
    {
        Room outside, theatre, pub, lab, office;
        String[] answers1 = {"A. Fordi ingen kan lide ham", "B. Fordi kage ", "C. Fordi all elsker ham"};
        Quiz quiz1 = new Quiz("Hvorfor er Sindahl adoptered", answers1, "C","Sindahl");
        PlaceableObject placeableObject1 = new Information("test1", "Tasdasdasdasd asdasd asd as dasd as dasd asd adad as");
        PlaceableObject placeableObject2 = new WindMillPart("Windmill-Wing", 21, "This is one of the windmill wings");
        ArrayList<PlaceableObject> itemsInOutside = new ArrayList<PlaceableObject>();
        itemsInOutside.add(placeableObject1);
        itemsInOutside.add(placeableObject2);


        outside = new Room("outside the main entrance of the university", itemsInOutside);
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab", quiz1);
        office = new Room("in the computing admin office");
        this.assembleRoom = new Room("in the room where you assemble your windmill");

        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", this.assembleRoom);

        this.assembleRoom.setExit("south", outside);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        this.currentRoom = outside;
    }

    public void play() //metode, der sætter exit-conditionen
    {
        printWelcome();


        boolean finished = false;

        while (!finished) {
            Command command = this.parser.getCommand();
            finished = processCommand(command);
        }
        if (this.gameCompleted) {
            System.out.println("Congratulations you have successfully completed the game and therefore you are smarter than Sindahl");
        }

        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(this.currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) //den tjekker konsol-inputsene og tjekker, om de passer med dem i enum'et
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.COLLECT) {
            this.currentRoom.collectItem(command, this.playerInventory);
        } else if (commandWord == CommandWord.INSPECT) {
            this.playerInventory.inspectItem(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            this.playerInventory.printInventory();
        } else if (commandWord == CommandWord.DoQUIZ) {
            this.currentRoom.doQuizInRoom();
        } else if (commandWord == CommandWord.ASSEMBLE) {
            if (successfulAssemble()) {
                this.gameCompleted = true;
                return true;
            }
            return false;
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) { //hvis der ikke er et secondWord, printes "Go where?"
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = this.currentRoom.getExit(direction); //henter det rum, der er i den givne direction

        if (nextRoom == null) { //tjekker, om der er rum i den givne direction
            System.out.println("There is no door!");
        } else {
            this.currentRoom = nextRoom;
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

    private boolean quit(Command command)  //metode til at stoppe spillet
    {
        if (command.hasSecondWord()) { //tjekker, om der er et ord efter.
            System.out.println("Quit what?");
            return false;
        } else { //returnerer "true", som stopper spillet via de andre metoder
            return true;
        }
    }
}
