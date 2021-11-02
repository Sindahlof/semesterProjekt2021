package worldofzuul;

public class Player extends PlaceableObject {
    private int[][] location;
    public Player(String playerName, int[][] location) {
        super(playerName);
        this.location = location;
    }

    @Override
    public void print() {

    }
}
