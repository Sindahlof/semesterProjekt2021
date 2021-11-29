package domain;

public class Player extends PlaceableObject {

    private int health;

    public Player(String playerName, int y, int x) {
        super(playerName, y, x);
        health = 3;
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
}
