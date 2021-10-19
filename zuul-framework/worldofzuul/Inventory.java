package worldofzuul;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> slots;

    //No arg constructor to initialise item Arraylist slots.
    Inventory() {
        this.slots = new ArrayList<Item>();
    }

    // A getter for slots
    public ArrayList<Item> getSlots() {
        return this.slots;
    }

    // Method to add items to the inventory
    public void addItem(Item item) {
        this.slots.add(item);
    }

    // Method to remove items from the inventory
    public void removeItem(Item item) {
        this.slots.remove(item);
    }

    //Method to print the contents of inventory
    public void printInventory() {
        System.out.println("Inventory:");
        for (Item item : this.slots) {
            System.out.println(item + ", ");
        }
    }

}
