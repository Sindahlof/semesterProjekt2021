package worldofzuul;

public class Player extends PlaceableObject {

    public Player(String playerName,int y,int x) {
        super(playerName,y,x);
    }

    @Override
    public void print() {
        System.out.println("The player is located at: " + getPosistion());
    }
}
