package worldofzuul;

public abstract class PlaceableObject {
    private Position position;
    private String objectName;

    //Constructor for Item which just sets the value of the String itemName
    public PlaceableObject(String objectName, int y, int x) {
        this.position = new Position(y,x);
        this.objectName = objectName;

    }

    //Getter for getting itemName
    public String getObjectName() {
        return this.objectName;
    }

    public abstract void print();

    public Position getPosistion(){
        return this.position;
    }
}