package worldofzuul; //den package vi bruger

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Game newGame = new Game(); //laver et nyt objekt der herdder game
        newGame.play(); //kalder en construcotr som hedder play fra klassen Game

        System.out.println("\n krav - Make a way to move faster - C");

        /*
        Hvis man vil se alle keys i HashMap'et
        CommandWords obj1 = new CommandWords();
        obj1.showAll();
        */
    }
}
