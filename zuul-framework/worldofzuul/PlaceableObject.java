package worldofzuul;

public abstract class PlaceableObject {
    private int y;
    private int x;
    private String itemName;

    //Constructor for Item which just sets the value of the String itemName
    public PlaceableObject(String itemName,int y,int x) {
        this.x = x;
        this.y = y;
        this.itemName = itemName;

    }

    //Getter for getting itemName
    public String getItemName() {
        return this.itemName;
    }

    public abstract void print();

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}