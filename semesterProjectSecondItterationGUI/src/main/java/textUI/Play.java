package textUI;

import domain.*;

import java.util.Scanner;

public class Play {

    private String output;
    private boolean finished;

    public Play() {
        this.finished = false;
    }


    public void play(Game game) {//Method which determines when the game is over

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
                    game.getPlayer1().setHealth(10);
                    difficultySelected = true;
                    break;
                case "normal":
                    System.out.println("You have selected the normal difficulty, you have 5 health.");
                    game.getPlayer1().setHealth(5);
                    difficultySelected = true;
                    break;
                case "hard":
                    System.out.println("You have selected the hard difficulty, you have 2 health.");
                    game.getPlayer1().setHealth(2);
                    difficultySelected = true;
                    break;
                default:
                    System.out.print("Unknown difficulty, try again. \n>");
            }
        }

        System.out.println(game.welcome());
        PrintGrid grid = new PrintGrid();
        System.out.println(grid.printGrid(game));

        while (!(this.finished)) {
            game.getCurrentRoom().constructGrid(game.getPlayer1());
            Command command = game.getParser().getCommand();
            commandHandlerUI(game, command);
        }

        //The game has 3 endings
        if (game.getGameCompleted()) { //1st ending the game is completed and you win
            System.out.println("Congratulations you have successfully constructed the windmill and thereby completed the game!");
        }
        if (game.getDead()) { // 2nd ending the player is dead and you lose
            System.out.println("You have lost all your health and therefore died.");
        }
        // Last ending the player quits without completing or dying
        System.out.println("Thank you for playing.  Goodbye.");
    }

    public void commandHandlerUI(Game game, Command command) {
        String data = game.processCommand(command);
        PrintGrid grid = new PrintGrid();

        switch (data) {
            case "1":
                printHelp(game);
                break;

            case "2":
                System.out.println("There is no quiz in this room.");
                break;

            case "3":
                System.out.println("The quiz in this room has already been completed.");
                break;

            case "4":
                System.out.println("Congratulations you have completed the quiz, a windmill part has been unlocked.");
                System.out.println(grid.printGrid(game));
                break;

            case "5":


            case "6":
                System.out.println(game.getPlayerInventory().returnInventory());
                break;

            case "7":
                System.out.println("You have " + game.getPlayer1().getHealth() + " health left");
                break;

            case "8":


                if (game.successfulAssemble() == 2) {
                    System.out.println(("You have not collected all windmill-parts"));
                }
                if (game.successfulAssemble() == 3) {
                    System.out.println("You are in the wrong room head to the assemble room");
                }
                break;

            case "10":
                String move = movePlayerUIParser(game.getCurrentRoom().getMovePlayerUIhandler());
                if(move == "1"){
                    System.out.println("Move player where?");
                    break;
                }
                if(move == "2"){
                    System.out.println("You cannot move there");
                    break;
                }
                if(move == "3"){
                    System.out.println("Unknown direction");
                    break;
                }
                if (move == "0"){
                    System.out.println(grid.printGrid(game));
                    System.out.println(game.getCurrentRoom().checkPlayerPosition(game.getPlayer1()));
                    break;
                }
            case "11":
                String a = game.getCurrentRoom().collectObject(game.getPlayerInventory(), game.getPlayer1());
                if (!(a == "df")) {
                    System.out.println("you have collected " + a);
                } else {
                    System.out.println("you are not standing on an item");
                }
                break;

            case "1000":
                System.out.println("I dont know what that means ....");

            case"12":
                String exit = game.exitRoom();
                if (exit == "1"){
                    System.out.println("You are not at an exit");
                }
                if (exit == "2"){
                    System.out.println("There is no door!");
                }
                if (exit == "3"){
                    System.out.println(game.getCurrentRoom().getLongDescription(game.getPlayer1()));
                    System.out.println(grid.printGrid(game));
                }
                break;

            case "13":
                System.out.println("Quit what?");
                break;

            case "14":
                setFinished();
                break;
            case "0":
                System.out.println("i don't know what that means......");
        }

        if (game.getPlayer1().getHealth() == 0){
            setFinished();
        }

    }

    public void printHelp (Game game){
        System.out.println("These are the possible commands in the game:");
        game.getParser().showCommands();
    }

    public String movePlayerUIParser (int num){
        if (num == 1){
            return "1";
        }
        if(num == 2){
            return "2";
        }
        if (num == 3){
            return "3";
        }
        return "0";
    }

    private void setFinished(){
        this.finished = true;
    }
}