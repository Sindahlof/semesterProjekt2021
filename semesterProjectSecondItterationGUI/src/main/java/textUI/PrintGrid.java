package textUI;

import domain.*;


public class PrintGrid {



    public PrintGrid(){
    }

    public String printGrid(Game game) {

       game.getCurrentRoom().constructGrid(game.getPlayer1());

        String print = "";
        for (int y = 0; y < game.getCurrentRoom().getGrid().length; y++) { // Does one vertical line at the time
            if (y == 0) {
                print += "\n" + game.getCurrentRoom().verticalLine() + "\n";
            }
            for (int x = 0; x < game.getCurrentRoom().getGrid()[y].length; x++) { //Loop for placing each symbol in the horizontal line
                if (x == 0) { //Starts with placing the end border symbolized by |
                    print += "|";
                }
                if (game.getCurrentRoom().getGrid() [y][x] == '\0') { //If the spot in the grid is empty then add a (    |).
                    print += " " + " " + " |";
                } else {
                    print += " " + game.getCurrentRoom().getGrid()[y][x] + " |"; //If there is something in the grid then print that symbol e.q. (" P |")
                }
            }
            print += "\n" + game.getCurrentRoom().verticalLine() + "\n"; // when it's done with one vertical line separate it by --------------
        }
        return print;
    }









}
