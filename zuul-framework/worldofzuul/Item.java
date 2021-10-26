package worldofzuul;

public abstract class Item {
    private String itemName;

    //Constructor for Item which just sets the value of the String itemName
    public Item(String itemName) {
        this.itemName = itemName;

    }

    //Getter for getting itemName
    public String getItemName() {
        return this.itemName;
    }

    public abstract void print();
}