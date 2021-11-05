package worldofzuul; //den package vi bruger

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Game newGame = new Game(); //laver et nyt objekt der herdder game
        newGame.play(); //kalder en method som hedder play fra klassen Game

        System.out.println("\n krav - Make a way to move faster - C");
        System.out.println("\n krav - Lock rooms until a quiz has been completed - C");
    }
}
