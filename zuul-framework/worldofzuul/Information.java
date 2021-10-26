package worldofzuul;

public class Information extends Item {

    public String content;

    public Information(String itemName,String content) {
        super(itemName);
        this.content=content;
    }

    public String getContent() {
        String text = this.getItemName() + "\n" + this.content;
        return text;
    }

    public void printContent () {
        System.out.println(content);
    }

}
