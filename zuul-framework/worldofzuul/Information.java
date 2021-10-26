package worldofzuul;

public class Information extends Item {

    public String content;

    public Information(String itemName, String content) {
        super(itemName);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void printContent() {
        String text = this.getItemName() + "\n" + this.content;

        System.out.println(text);
    }

}
