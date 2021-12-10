package domain;

public class WindMillPart extends PlaceableObject {

    private String description;
    private int id;

    public WindMillPart(String objectName, int id, String description, int y, int x) {
        super(objectName, y, x);
        this.id = id;
        this.description = description;
    }

    public String getWindMillPart() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String print() {
        return (this.getObjectName() + ". With the ID: " + this.id + "\n" + this.description);
    }

}