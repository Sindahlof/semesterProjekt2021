package worldofzuul;

public class Information extends PlaceableObject {

    private String content;

    public Information(String itemName, String content, int y, int x) {
        super(itemName,y,x);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public void print() {
        String text = this.getObjectName() + "\n" + this.content;
        System.out.println(text);
    }

}
