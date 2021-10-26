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
        return this.description;
    }

    public int getId()
    {
        return this.id;
    }

    @Override
    public void print()
    {
        String text = this.getItemName() + "\n" + this.description;
        System.out.println(text);
    }
}