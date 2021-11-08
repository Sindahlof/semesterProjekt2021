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
        String[] answers1 = {"A. 31%", "B. 20%", "C. 29%"};
        String[] answers2 = {"A. 4-10%", "B. 11-17%", "C. 18-24%"};
        String[] answers3 = {"A. 26-30 billion kroner", "B. 21-25 billion kroner", "C. 16-20 billion kroner"};
        String[] answers4 = {"A. Global warming, rising water levels and more violent climate", "B. Better conditions for animal and plant life", "C. Better air quality"};
        String[] answers5 = {"A. 320 GW", "B. 280 GW", "C. 240 GW"};
        String[] answers6 = {"A. 2 million", "B. 2,5 million", "C. 3 million"};

        Quiz quiz1 = new Quiz("What percentage of global electricity production came from green energy sources in 2020? ", answers1, "C", "Electricity production");
        Quiz quiz2 = new Quiz("How much CO2 does a person in Denmark emit on average in a year?", answers2, "B", "CO2 Emissions");
        Quiz quiz3 = new Quiz("How much will the green transition cost in Denmark?", answers3, "A", "Green transition cost");
        Quiz quiz4 = new Quiz("What consequences does CO2 emissions have, among other things?", answers4, "A", "Consequences of CO2 emissions");
        Quiz quiz5 = new Quiz("How much did green energy production increase globally in 2020?", answers5, "B", "Green energy production");
        Quiz quiz6 = new Quiz("How many people die annually due to air pollution on a global basis?", answers6, "B", "Air pollution");

        PlaceableObject information1 = new Information("Article1", "" +
                "This article is about green energy sources in 2020." +
                "\nOnly 29% of the global energy production came from green energy sources in 2020. That rose from 27% in 2019.", 1, 2);
        PlaceableObject information2 = new Information("Article2", "" +
                "This article is about how much CO2 a person in general releases." +
                "\nNew studies show that a regular person in Denmark releases somewhere between 11-17% CO2 in a span of 1 year." +
                "The CO2 released is from food-production, transportation etc.", 1, 2);
        PlaceableObject information3 = new Information("Article3", "" +
                "This article is about how much a green conversion will cost." +
                "\nIt is estimated that a 70% green conversion by 2030 in Denmark will cost around 26-30 billion kroner. " +
                "The calculations are made by a think tank Cepos and in collaboration with Dansk Energi.", 1, 2);
        PlaceableObject information4 = new Information("Article4", "" +
                "This article is about the consequences of CO2 emissions." +
                "\nSeveral reports and analyzes demonstrate the link between CO2 emissions and other greenhouse gases in the atmosphere, " +
                "along with the depletion of the ozone layer, rising temperatures, the clearing of rainforests, the melting of ice caps, " +
                "elevated water levels and the critical consequences of global warming.", 1, 2);
        PlaceableObject information5 = new Information("Article5", "" +
                "This article is about how many deaths pollution causes." +
                "\nEvery year, 2.5 million people worldwide die as a result of air pollution. " +
                "This makes pollution in particularly large cities from traffic and industry one of the most important environmental risk factors when it comes to human health. " +
                "This is how it sounds from 30 researchers from the USA, England, Italy, Japan and New Zealand, who are behind a new study. ", 1, 2);
        PlaceableObject information6 = new Information("Article6", "" +
                "This article is about the expansion of green energy." +
                "\nGlobally, green energy production increased by 280GW in 2020. This is the largest increase in the last 20 years.", 1, 2);
        PlaceableObject windMillPart1 = new WindMillPart("Windmill-wing", 1, "This is the first windmill wing.", 2, 1);
        PlaceableObject windMillPart2 = new WindMillPart("Windmill-wing", 2, "This is the second windmill wing.", 2, 1);
        PlaceableObject windMillPart3 = new WindMillPart("Windmill-wing", 3, "This is the third windmill wing.", 2, 1);
        PlaceableObject windMillPart4 = new WindMillPart("Windmill-head", 4, "This is the head of the windmill. This is where the wings are connected.", 2, 1);
        PlaceableObject windMillPart5 = new WindMillPart("Windmill-tower", 5, "This is the tower of the windmill. This is where the head is connected.", 2, 1);
        PlaceableObject windMillPart6 = new WindMillPart("Windmill-foundation", 6, "This is the foundation of the windmill. This is where the tower is connected.", 2, 1);

        this.player1 = new Player("Player 1", 2, 2);


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

        outside.addObjectsInRoom(information1);
        outside.addObjectsInRoom(windMillPart1);
        outside.addQuizToRoom(quiz2);

        this.assembleRoom.setExit("south", outside);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("west", getRekt);

        getRekt.setExit("east", pub);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addObjectsInRoom(windMillPart1);
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
            System.out.println("Congratulations you have successfully constructed the windmill and thereby completed the game!");
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
