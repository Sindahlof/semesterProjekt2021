package worldofzuul;

public class Information extends PlaceableObject {

    private String content;
    private int id;

    public Information(String itemName, String content) {
        super(itemName);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public void print() {
        String text = this.getItemName() + "\n" + this.content;
        System.out.println(text);
    }

}
