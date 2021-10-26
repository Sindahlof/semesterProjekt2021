package worldofzuul;

public class WindMillPart extends Item {

    private String description;
    private int id;

    public WindMillPart(String description, String itemName, int id)
    {
        super(itemName);
        this.description = description;
        this.id = id;
    }

    public String getWindMillPart()
    {
        String text = this.getItemName() + "\n" + this.description;
        return text;
    }

    public int getId()
    {
        return this.id;
    }

    public void printWindMillPart()
    {
        System.out.println(this.description);
    }
}