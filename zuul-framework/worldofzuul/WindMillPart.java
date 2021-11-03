package worldofzuul;

public class WindMillPart extends PlaceableObject {

    private String description;
    private int id;

    public WindMillPart(String itemName,int id, String description,int y,int x) {
        super(itemName,y,x);
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
    public void print() {
        String text = this.getItemName() +" With the ID: "+ this.id + "\n" + this.description;
        System.out.println(text);
    }
}