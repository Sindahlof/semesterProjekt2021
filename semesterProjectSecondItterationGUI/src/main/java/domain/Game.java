package domain;

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
        Room startingRoom, quizRoom1, quizRoom2, quizRoom3, quizRoom4, quizRoom5, quizRoom6, townSquare, park, cloverSt, harbor, queensSt, filler;
        String[] answers1 = {"A. 31%", "B. 20%", "C. 29%"};
        String[] answers2 = {"A. Energy that will not be replenished in a short timescale", "B. Energy that is replenished in a short timescale", "C. Energy that we can’t produce yet"};
        String[] answers3 = {"A. 26-30 billion kroner", "B. 21-25 billion kroner", "C. 16-20 billion kroner"};
        String[] answers4 = {"A. Pollution", "B. Not enough energy is created", "C. Not enough room"};
        String[] answers5 = {"A. 320 GW", "B. 280 GW", "C. 240 GW"};
        String[] answers6 = {"A. Coal power plants", "B. Nuclear power plants", "C. Wind power plants"};

        Quiz quiz1 = new Quiz("What percentage of global electricity production came from green energy sources in 2020? ", answers1, "C", "Electricity production");
        Quiz quiz2 = new Quiz("What is sustainable energy?", answers2, "B", "Sustainable energy");
        Quiz quiz3 = new Quiz("How much will the green transition cost in Denmark?", answers3, "A", "Green transition cost");
        Quiz quiz4 = new Quiz("What are some of the drawbacks of current energy production?", answers4, "A", "drawbacks of our energy production");
        Quiz quiz5 = new Quiz("How much did green energy production increase globally in 2020?", answers5, "B", "Green energy production");
        Quiz quiz6 = new Quiz("How is sustainable energy produced?", answers6, "C", "Sustainable energy production");

        PlaceableObject information1 = new Information("Article-1", "" +
                "This article is about green energy sources in 2020." +
                "\nOnly 29% of the global energy production came from green energy sources in 2020. That rose from 27% in 2019.", 1, 2);
        PlaceableObject information2 = new Information("Article-6", "" +
                "This article is about sustainable energy" +
                "\nSustainable energy is when the energy you produce right now, will still be able to power the future generations without reducing the quality of life." +
                "\nRenewable energy sources such as wind and solar are examples of sustainable energy sources.", 2, 1);
        PlaceableObject information3 = new Information("Article-3", "" +
                "This article is about how much a green conversion will cost." +
                "\nIt is estimated that a 70% green conversion by 2030 in Denmark will cost around 26-30 billion kroner. " +
                "\nThe calculations are made by a think tank Cepos and in collaboration with Dansk Energi.", 1, 2);
        PlaceableObject information4 = new Information("Article-4", "" +
                "This article is about the drawbacks of current energy production" +
                "\nThe cons of burning coal to produce energy is that with burning coal comes a lot of pollution into the air. " +
                "\nAnd by mining coal some byproducts like mercury and arsenic also get uncovered. " +
                "\nAlso, the quantity of coal is finite, which means that we will run out of coal at some point if we keep mining coal. ", 0, 2);
        PlaceableObject information5 = new Information("Article-5", "" +
                "This article is about how many deaths pollution causes." +
                "\nEvery year, 2.5 million people worldwide die as a result of air pollution. " +
                "\nThis makes pollution in particularly large cities from traffic and industry one of the most important environmental risk factors when it comes to human health. " +
                "\nThis is how it sounds from 30 researchers from the USA, England, Italy, Japan and New Zealand, who are behind a new study. ", 4, 1);
        PlaceableObject information6 = new Information("Article-2", "" +
                "This article is about sustainable energy sources" +
                "\nSustainable energy comes from renewable sources. Renewable sources are things like sunlight, wind, water and geothermal." +
                "\nThese are replenished in a timescale of a human lifetime, which means that they aren’t in limited supply." +
                "\nThe most well-known renewable energy production technologies are hydropower, wind power, solar power and geothermal power. ", 2, 2);
        PlaceableObject windMillPart1 = new WindMillPart("Windmill-wing-1", 1, "This is the first windmill wing.", 2, 2);
        PlaceableObject windMillPart2 = new WindMillPart("Windmill-wing-2", 2, "This is the second windmill wing.", 1, 2);
        PlaceableObject windMillPart3 = new WindMillPart("Windmill-wing-3", 3, "This is the third windmill wing.", 2, 1);
        PlaceableObject windMillPart4 = new WindMillPart("Windmill-head", 4, "This is the head of the windmill. This is where the wings are connected.", 1, 0);
        PlaceableObject windMillPart5 = new WindMillPart("Windmill-tower", 5, "This is the tower of the windmill. This is where the head is connected.", 1, 1);
        PlaceableObject windMillPart6 = new WindMillPart("Windmill-foundation", 6, "This is the foundation of the windmill. This is where the tower is connected.", 1, 2);

        this.player1 = new Player("Player 1", 2, 2);


        this.assembleRoom = new Room("green fields", "on a lush green field perfect for assembling a windmill.", 3, 3);
        startingRoom = new Room("mayor office", "at the mayor office.", 5, 5);
        quizRoom1 = new Room("secretary office", "at the secretary office.", 5, 5);
        quizRoom2 = new Room("Harbor", "at the harbor, there is a windmill part here complete the quiz to collect the part.", 3, 3);
        quizRoom3 = new Room("public restroom", "at the public restroom, there is a windmill part here complete the quiz to collect the part. ", 3, 3);
        quizRoom4 = new Room("volkswagen mechanic", "at the Volkswagen mechanic, there is a windmill part here complete the quiz to collect the part.", 3, 3);
        quizRoom5 = new Room("playground", "at the Playground, there is a windmill part here complete the quiz to collect the part.", 3, 3);
        quizRoom6 = new Room("university of Engineering and Science", "at the University of Engineering and Science, there is a windmill part here complete the quiz to collect the part.", 3, 3);
        townSquare = new Room("town square", "at the town square.", 3, 5);
        park = new Room("park", "at the park.", 5, 3);
        cloverSt = new Room("clover St", "at the Clover St.", 3, 5);
        harbor = new Room("harbor", "at the harbor.", 3, 5);
        queensSt = new Room("queens St", "at the Queens St.", 5, 5);


        this.assembleRoom.setExit("south", queensSt);

        startingRoom.setExit("south", quizRoom1);
        startingRoom.addObjectsInRoom(information1);

        quizRoom1.setExit("north", startingRoom);
        quizRoom1.setExit("south", townSquare);
        quizRoom1.setExit("west", cloverSt);
        quizRoom1.setExit("east", queensSt);
        quizRoom1.addQuizToRoom(quiz1);
        quizRoom1.addObjectsInRoom(windMillPart1);

        townSquare.setExit("south", quizRoom3);
        townSquare.setExit("north", quizRoom1);
        townSquare.setExit("east", harbor);
        townSquare.setExit("west", park);
        townSquare.addObjectsInRoom(information3);

        cloverSt.setExit("east", quizRoom1);
        cloverSt.setExit("west", quizRoom4);
        cloverSt.addObjectsInRoom(information4);

        quizRoom4.setExit("east", cloverSt);
        quizRoom4.addQuizToRoom(quiz4);
        quizRoom4.addObjectsInRoom(windMillPart4);

        queensSt.setExit("east", quizRoom6);
        queensSt.setExit("west", quizRoom1);
        queensSt.setExit("north", this.assembleRoom);
        queensSt.addObjectsInRoom(information6);

        quizRoom6.setExit("west", queensSt);
        quizRoom6.addQuizToRoom(quiz6);
        quizRoom6.addObjectsInRoom(windMillPart6);

        harbor.setExit("west", townSquare);
        harbor.setExit("east", quizRoom2);
        harbor.addObjectsInRoom(information2);

        quizRoom2.setExit("west", harbor);
        quizRoom2.addObjectsInRoom(windMillPart2);
        quizRoom2.addQuizToRoom(quiz2);

        park.setExit("west", quizRoom5);
        park.setExit("east", townSquare);
        park.addObjectsInRoom(information5);

        quizRoom5.setExit("east", park);
        quizRoom5.addQuizToRoom(quiz5);
        quizRoom5.addObjectsInRoom(windMillPart5);

        quizRoom3.setExit("north", townSquare);
        quizRoom3.addQuizToRoom(quiz3);
        quizRoom3.addObjectsInRoom(windMillPart3);

        this.currentRoom = startingRoom;
        startingRoom.addObjectsInRoom(this.player1);
    }

    public void play() {//Method which determines when the game is over

        System.out.println("Welcome to the World of Power!");
        System.out.println("World of Power is a game about the UN's 7th world goal; Affordable and clean energy. " +
                "\nYou have been tasked by the mayor to help assemble a brand-new state of the art windmill." +
                " \nTo do so you’ll have to walk around the city and collect the necessary parts. " +
                "\nThese parts will be visible after you have answered a quiz. " +
                "\nYou can gather information about the quiz’ by collecting and inspecting articles throughout the town. ");

        System.out.print("To start the game please choose one of the following difficulties. EASY, NORMAL, HARD: \n>");

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
                "P = Player" +
                "\nE = Exit" +
                "\nA = Article" +
                "\nW = Windmill part");
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
                    System.out.println("The quiz in this room has already been completed.");
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
                System.out.println("You have " + this.player1.getHealth() + " health left"); //prints the players health
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
