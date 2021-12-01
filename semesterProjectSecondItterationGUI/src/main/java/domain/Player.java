package domain;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Player extends PlaceableObject {

    private int health;
    private String img;

    public Player(String playerName, int y, int x,String absoultePath) {
        super(playerName, y, x);
        health = 3;
        this.img = absoultePath;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String print() {
        return "The player is located at: " + getPosistion() + " and has " + getHealth() + " Health";
    }
    public Image getImage () {
    Image image = new Image(this.img);
    return image;
    }
}
