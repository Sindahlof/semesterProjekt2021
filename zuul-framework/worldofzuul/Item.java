package worldofzuul;

public abstract class Item {
    private String itemDescription;

    public Item(String itemDescription) {
        this.itemDescription = itemDescription;
    }


    public void printDescription() {
        System.out.println(itemDescription);
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

}