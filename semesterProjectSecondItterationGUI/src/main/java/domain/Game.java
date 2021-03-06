package domain;

import javafx.scene.layout.GridPane;

public class Game //her "skabes" klassen Game
{
    //der laves 2 attributter, deres datatyper er taget fra andre klasser
    private Parser parser;
    private Room currentRoom;
    private final Inventory playerInventory;
    private boolean gameCompleted, dead;
    private Room assembleRoom;
    private Player player1;
    private PlaceableObject information1;
    private PlaceableObject information2;
    private PlaceableObject information3;
    private PlaceableObject information4;
    private PlaceableObject information5;
    private PlaceableObject information6;

    private PlaceableObject windMillPart1;
    private PlaceableObject windMillPart2;
    private PlaceableObject windMillPart3;
    private PlaceableObject windMillPart4;
    private PlaceableObject windMillPart5;
    private PlaceableObject windMillPart6;


    public Game() { //constructor Game defineres
        this.playerInventory = new Inventory(); //Initializer player's inventory
        createRooms();
        this.gameCompleted = false;
        this.dead = false;
        this.parser = new Parser(); //attributten parser sættes til at være klassen Parser
    }

    public void createRooms() {//en metode til at lave rum
        GridPane restroom = new GridPane();
        GridPane secretaryOffice = new GridPane();
        GridPane Room = new GridPane();
        GridPane assemblyRoom = new GridPane();
        GridPane harbor = new GridPane();
        GridPane volkswagenMechanic = new GridPane();
        GridPane playground = new GridPane();
        GridPane university = new GridPane();
        GridPane townSquare_ = new GridPane();
        GridPane park_ = new GridPane();
        GridPane cloverSt_ = new GridPane();
        GridPane queenST = new GridPane();
        GridPane harbor_st = new GridPane();


        Room startingRoom, quizRoom1, quizRoom2, quizRoom3, quizRoom4, quizRoom5, quizRoom6, townSquare, park, cloverSt, harborST, queensSt;
        String[] answers1 = {"A. 31%", "B. 20%", "C. 29%"};
        String[] answers2 = {"A. Energy that will not be replenished in a short timescale", "B. Energy that is replenished in a short timescale", "C. Energy that we can’t produce yet"};
        String[] answers3 = {"A. 26-30 billion kroner", "B. 21-25 billion kroner", "C. 16-20 billion kroner"};
        String[] answers4 = {"A. Pollution", "B. Not enough energy is created", "C. Not enough room"};
        String[] answers5 = {"A. 2 million", "B. 2.5 million", "C. 3 million"};
        String[] answers6 = {"A. Coal power plants", "B. Nuclear power plants", "C. Wind power plants"};

        Quiz quiz1 = new Quiz("What percentage of global electricity production came from green energy sources in 2020? ", answers1, "C", "Electricity production");
        Quiz quiz2 = new Quiz("What is sustainable energy?", answers2, "B", "Sustainable energy");
        Quiz quiz3 = new Quiz("How much will the green transition cost in Denmark?", answers3, "A", "Green transition cost");
        Quiz quiz4 = new Quiz("What are some of the drawbacks of current energy production?", answers4, "A", "Drawbacks of our energy production");
        Quiz quiz5 = new Quiz("How many people die annually due to air pollution on a global basis?", answers5, "B", "Deaths due to pollution");
        Quiz quiz6 = new Quiz("How is sustainable energy produced?", answers6, "C", "Sustainable energy production");

        this.information1 = new Information("Article-1", "" +
                "This article is about green energy sources in 2020. " +
                "Only 29% of the global energy production came from green energy sources in 2020. That rose from 27% in 2019.", 1, 2);
        this.information2 = new Information("Article-6", "" +
                "This article is about sustainable energy. " +
                "Sustainable energy is when the energy you produce right now, will still be able to power the future generations without reducing the quality of life. " +
                "Renewable energy sources such as wind and solar are examples of sustainable energy sources.", 2, 1);
        this.information3 = new Information("Article-3", "" +
                "This article is about how much a green conversion will cost. " +
                "It is estimated that a 70% green conversion by 2030 in Denmark will cost around 26-30 billion kroner. " +
                "The calculations are made by a think tank Cepos and in collaboration with Dansk Energi.", 1, 2);
        this.information4 = new Information("Article-4", "" +
                "This article is about the drawbacks of current energy production. " +
                "The cons of burning coal to produce energy is that with burning coal comes a lot of pollution into the air. " +
                "And by mining coal some byproducts like mercury and arsenic also get uncovered. " +
                "Also, the quantity of coal is finite, which means that we will run out of coal at some point if we keep mining coal.", 1, 1);
        this.information5 = new Information("Article-5", "" +
                "This article is about how many deaths pollution causes. " +
                "Every year, 2.5 million people worldwide die as a result of air pollution. " +
                "This makes pollution in particularly large cities from traffic and industry one of the most important environmental risk factors when it comes to human health. " +
                "This is how it sounds from 30 researchers from the USA, England, Italy, Japan and New Zealand, who are behind a new study.", 4, 1);
        this.information6 = new Information("Article-2", "" +
                "This article is about sustainable energy sources. Sustainable energy comes from renewable sources. Renewable sources are things like sunlight, wind, water and geothermal. " +
                "These are replenished in a timescale of a human lifetime, which means that they aren’t in limited supply. " +
                "The most well-known renewable energy production technologies are hydropower, wind power, solar power and geothermal power.", 2, 2);
        this.windMillPart1 = new WindMillPart("Windmill-wing-1", 1, "This is the first windmill wing.", 2, 2);
        this.windMillPart2 = new WindMillPart("Windmill-wing-2", 2, "This is the second windmill wing.", 1, 2);
        this.windMillPart3 = new WindMillPart("Windmill-wing-3", 3, "This is the third windmill wing.", 2, 1);
        this.windMillPart4 = new WindMillPart("Windmill-motor", 4, "This is the motor of the windmill.", 4, 4);
        this.windMillPart5 = new WindMillPart("Windmill-rod part 1", 5, "This is the top rod of the windmill.", 1, 1);
        this.windMillPart6 = new WindMillPart("Windmill-rod part 2", 6, "This is the bottom rod and foundation of the windmill.", 1, 2);

        this.player1 = new Player("Player 1", 6, 0, "/player.png");

        this.assembleRoom = new Room("green fields", "on a lush green field perfect for assembling a windmill.", 15, 23, assemblyRoom);
        startingRoom = new Room("Mayor Office", "at the mayor office.", 8, 8, Room);
        quizRoom1 = new Room("secretary Office", "at the secretary office.", 7, 7, secretaryOffice);
        quizRoom2 = new Room("Harbor", "at the harbor, there is a windmill part here complete the quiz to collect the part.", 12, 12, harbor);
        quizRoom3 = new Room("public restroom", "at the public restroom, there is a windmill part here complete the quiz to collect the part. ", 5, 6, restroom);
        quizRoom4 = new Room("volkswagen mechanic", "at the Volkswagen mechanic, there is a windmill part here complete the quiz to collect the part.", 8, 10, volkswagenMechanic);
        quizRoom5 = new Room("playground", "at the Playground, there is a windmill part here complete the quiz to collect the part.", 9, 10, playground);
        quizRoom6 = new Room("university of Engineering and Science", "at the University of Engineering and Science, there is a windmill part here complete the quiz to collect the part.", 8, 9, university);
        townSquare = new Room("town square", "at the town square.", 14, 23, townSquare_);
        park = new Room("park", "at the park.", 11, 18, park_);
        cloverSt = new Room("clover St", "at the Clover St.", 9, 18, cloverSt_);
        harborST = new Room("harbor St", "at the harbor St", 9, 17, harbor_st);
        queensSt = new Room("queens St", "at the Queens St.", 8, 15, queenST);


        this.assembleRoom.setExit("south", queensSt);

        startingRoom.setExit("south", quizRoom1);
        startingRoom.addObjectsInRoom(this.information1);

        quizRoom1.setExit("north", startingRoom);
        quizRoom1.setExit("south", townSquare);
        quizRoom1.setExit("west", cloverSt);
        quizRoom1.setExit("east", queensSt);
        quizRoom1.addQuizToRoom(quiz1);
        quizRoom1.addObjectsInRoom(this.windMillPart1);

        townSquare.setExit("south", quizRoom3);
        townSquare.setExit("north", quizRoom1);
        townSquare.setExit("east", harborST);
        townSquare.setExit("west", park);
        townSquare.addObjectsInRoom(this.information3);

        cloverSt.setExit("east", quizRoom1);
        cloverSt.setExit("west", quizRoom4);
        cloverSt.addObjectsInRoom(this.information4);

        quizRoom4.setExit("east", cloverSt);
        quizRoom4.addQuizToRoom(quiz4);
        quizRoom4.addObjectsInRoom(this.windMillPart4);

        queensSt.setExit("east", quizRoom6);
        queensSt.setExit("west", quizRoom1);
        queensSt.setExit("north", this.assembleRoom);
        queensSt.addObjectsInRoom(this.information6);

        quizRoom6.setExit("west", queensSt);
        quizRoom6.addQuizToRoom(quiz6);
        quizRoom6.addObjectsInRoom(this.windMillPart6);

        harborST.setExit("west", townSquare);
        harborST.setExit("east", quizRoom2);
        harborST.addObjectsInRoom(this.information2);

        quizRoom2.setExit("west", harborST);
        quizRoom2.addObjectsInRoom(this.windMillPart2);
        quizRoom2.addQuizToRoom(quiz2);

        park.setExit("west", quizRoom5);
        park.setExit("east", townSquare);
        park.addObjectsInRoom(this.information5);

        quizRoom5.setExit("east", park);
        quizRoom5.addQuizToRoom(quiz5);
        quizRoom5.addObjectsInRoom(this.windMillPart5);

        quizRoom3.setExit("north", townSquare);
        quizRoom3.addQuizToRoom(quiz3);
        quizRoom3.addObjectsInRoom(this.windMillPart3);

        this.currentRoom = startingRoom;
        this.currentRoom.constructGrid(this.player1);
    }


    public String welcome() {
        return "Meaning of the following symbols:\n" +
                "P = Player" +
                "\nE = Exit" +
                "\nA = Article" +
                "\nW = Windmill part" + "\n" + "Type '" +
                CommandWord.HELP +
                " to get a list of commands" +
                "\n" + this.currentRoom.getLongDescription(this.player1);
    }

    public String processCommand(Command command) { //den tjekker konsol-inputsene og tjekker, om de passer med dem i enum'et
        CommandWord commandWord = command.getCommandWord();

        //Switch case for processing the commands.
        switch (commandWord) {
            case HELP:
                return "1";

            case EXIT: //command for exiting a room
                return "12";

            case MOVE: //command for moving around in a room
                this.player1 = this.currentRoom.movePlayer(this.player1, command);
                return "10";

            case QUIT:
                if (!(quit(command)) == false) {
                    return "13";
                } else {
                    return "14";
                }


            case DoQUIZ: //command for doing the quiz
                if (this.currentRoom.getQuizInRoom() == null) { //checks if there is a quiz in the room
                    return "2";
                }
                if (this.currentRoom.getQuizInRoom().isCompletion()) { //checks is the quiz is ALREADY completed
                    return "3";
                }
                if (!(this.currentRoom.getQuizInRoom().isCompletion())) {
                    return "15";
                }
                if (this.player1.getHealth() == 0) { //Checks if the player has lost all his health
                    //if true changes boolean dead to true which triggers 3 ending;
                    this.dead = true;
                }

            case COLLECT:
                return "11";

            case INSPECT:
                //Calling a method to inspect an object in your inventory
                return "5";

            case ASSEMBLE:
                if (successfulAssemble() == 1) { //Calling a method to assemble the windmill
                    this.gameCompleted = true; // This triggers the first end condition
                } else {
                    return "8";
                }
                break;

            case INVENTORY:
                return "6";

            case HEALTH:
                return "7";

            default:
                return "0";
        }
        return "-1000";
    }


    public String exitRoom() { //Exit room method
        String direction = this.currentRoom.atWhichExit(this.player1); //First it gets which exit the player is at
        if (direction == null) { //If the player is not at any exits then it'll tell the player that
            return "1";
        }

        Room nextRoom = this.currentRoom.getExit(direction); //Gets the room which is linked to the exit the player is at

        if (nextRoom == null) { //Checks if there is a room at that exit location // this one is always false which makes it kind obsolete
            return "2";
        } else {
            this.currentRoom.removeObjectsInRoom(this.player1);            //Removes the player from the current room
            String exit = this.currentRoom.atWhichExit(getPlayer1());
            this.currentRoom = nextRoom;            //Changes to room to the next room
            this.player1.getPosistion().updatePosition(0, 0);
            this.currentRoom.constructGrid(this.getPlayer1());
            this.player1.getPosistion().updatePosition(this.currentRoom.getExitPosition(exit)); //Updates the players position to match the exit in the next room.
            // (e.g. Goes through the north gate exits at the south gate in the next room)
            this.currentRoom.addObjectsInRoom(this.player1); //Adds the player object to the new room
            return "3";
        }
    }

    public int successfulAssemble() { //Method used for assembling the windmill
        if (this.currentRoom == this.assembleRoom) { //Checks if the player is in the correct room for assemble
            if (this.playerInventory.collectedAllWindmillParts()) {//Checks if the player has collected all the windmill parts
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }

    }

    public boolean quit(Command command) {  //metode til at stoppe spillet
        if (command.hasSecondWord()) { //tjekker, om der er et ord efter.
            return false;
        } else { //returnerer "true", som stopper spillet via de andre metoder
            return true;
        }
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Parser getParser() {
        return this.parser;
    }

    public boolean getDead() {
        return this.dead;
    }

    public boolean getGameCompleted() {
        return this.gameCompleted;
    }

    public Inventory getPlayerInventory() {
        return this.playerInventory;
    }

    public PlaceableObject getInformation1() {
        return this.information1;
    }

    public PlaceableObject getInformation2() {
        return this.information2;
    }

    public PlaceableObject getInformation3() {
        return this.information3;
    }

    public PlaceableObject getInformation4() {
        return this.information4;
    }

    public PlaceableObject getInformation5() {
        return this.information5;
    }

    public PlaceableObject getInformation6() {
        return this.information6;
    }

    public PlaceableObject getWindMillPart1() {
        return windMillPart1;
    }

    public PlaceableObject getWindMillPart2() {
        return windMillPart2;
    }

    public PlaceableObject getWindMillPart3() {
        return windMillPart3;
    }

    public PlaceableObject getWindMillPart4() {
        return windMillPart4;
    }

    public PlaceableObject getWindMillPart5() {
        return windMillPart5;
    }

    public PlaceableObject getWindMillPart6() {
        return windMillPart6;
    }
}
