package textUI;

import domain.Game;
import domain.Room;


public class Test {
    public static void main(String[] args) {
        Game game = new Game(); //laver et nyt objekt der herdder game
        Play play = new Play();

        play.play(game);


    }

}
