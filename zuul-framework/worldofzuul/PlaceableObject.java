package worldofzuul;

public abstract class PlaceableObject {
    private Position position;
    private String objectName;

    //Constructor for placeableobject which just sets the value of the String objectName and the position attribut
    public PlaceableObject(String objectName, int y, int x) {
        this.position = new Position(y, x);
        this.objectName = objectName;

    }

    public String getObjectName() {
        return this.objectName;
    }

    public abstract void print();

    public Position getPosistion() {
        return this.position;
    }

    @Override
    public String toString() {
        return "PlaceableObject{" +
                "position=" + position +
                ", objectName='" + objectName + '\'' +
                '}';
    }
}