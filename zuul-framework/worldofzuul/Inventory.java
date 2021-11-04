package worldofzuul;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<PlaceableObject> slots;

    //No arg constructor to initialise item Arraylist slots.
    Inventory() {
        this.slots = new ArrayList<PlaceableObject>();
    }

    // A getter for slots
    public ArrayList<PlaceableObject> getInventory() {
        return this.slots;
    }

    // Method to add items to the inventory
    public void addItem(PlaceableObject placeableObject) {
        this.slots.add(placeableObject);
    }

    //Method to print the contents of inventory
    public void printInventory() {
        if (!(this.slots.isEmpty())) {
            System.out.println("You have the following item(s) in your inventory:");
            for (PlaceableObject placeableObject : this.slots) {
                System.out.print(placeableObject.getObjectName() + ", ");
            }
            System.out.println();
        } else {
            System.out.println("There are no items in your inventory LOOSER");
        }
    }

    public void inspectItem(Command command) {
        if (!(command.hasSecondWord())) {
            System.out.println("Inspect what item?");
            return;
        } else {
            for (PlaceableObject placeableObject : this.slots) {
                if (placeableObject.getObjectName().toUpperCase().equals(command.getSecondWord().toUpperCase())) {
                    placeableObject.print();
                    return;
                }
            }
        }
        System.out.println("You dont have that item in your inventory");
    }

    public boolean collectedAllWindmillParts(){
        int collectiveId = 0;

        for (PlaceableObject items : this.slots){
            if (items instanceof WindMillPart){
                collectiveId += ((WindMillPart) items).getId();
            }
        }
        if (collectiveId == 21){
            return true;
        }
        return false;
    }




}
