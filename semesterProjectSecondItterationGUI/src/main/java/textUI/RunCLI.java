package textUI;

import domain.Command;
import domain.Game;
import domain.Player;
import domain.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.*;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class RunCLI {
    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game(); //laver et nyt objekt der herdder game
       // Play play = new Play();
        // play.play(game);

        ImageView imp = new ImageView(game.getPlayer1().getImage());
        System.out.println(imp);

    }
}
