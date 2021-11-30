package textUI;

import domain.Command;
import domain.Game;
import domain.Player;
import domain.Room;


public class RunCLI {
    public static void main(String[] args) {
        Game game = new Game(); //laver et nyt objekt der herdder game
        Play play = new Play();

        play.play(game);
    }
}
