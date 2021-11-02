package worldofzuul;

public abstract class PlaceableObject {
    private int Y;
    private int X;
    private String itemName;

    //Constructor for Item which just sets the value of the String itemName
    public PlaceableObject(String itemName) {
        this.itemName = itemName;

    }

    //Getter for getting itemName
    public String getItemName() {
        return this.itemName;
    }

    public abstract void print();

    public void setY(int y) {
        this.Y = y;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return this.Y;
    }
    public int getX() {
        return this.X;
    }
}