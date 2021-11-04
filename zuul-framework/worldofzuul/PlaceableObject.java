package worldofzuul;

public abstract class PlaceableObject {
    private Posistion posistion;
    private String objectName;

    //Constructor for Item which just sets the value of the String itemName
    public PlaceableObject(String objectName, int y, int x) {
        this.posistion = new Posistion(y,x);
        this.objectName = objectName;

    }

    //Getter for getting itemName
    public String getObjectName() {
        return this.objectName;
    }

    public abstract void print();

    public Posistion getPosistion(){
        return this.posistion;
    }
}