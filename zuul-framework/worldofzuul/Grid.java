package worldofzuul;

import java.util.HashMap;

public class Grid {
    private String[][] grid;
    private HashMap<String,Posistion> doorLocationsInRoom;

    public Grid(int y, int x) {
        this.grid = new String[(y)][(x)];
        doorLocationsInRoom = new HashMap<String, Posistion>();
    }

    //Method to add objects to the grid, run this method after you addExits
    public void addObject(PlaceableObject object) {
        if (object instanceof Information) {
            this.grid[object.getY()][object.getX()] = "I";
        } else if (object instanceof WindMillPart) {
            this.grid[object.getY()][object.getX()] = "W";
        } else {
            this.grid[object.getY()][object.getX()] = "P";
        }
    }

    //Method to add exits to a grid, run this method before you addObjects
    public void addExits(HashMap<String, Room> exitsInRoom) {
        for (String exits : exitsInRoom.keySet()) {
            if (exits.equals("north")) {
                Posistion posistion = new Posistion(0,Math.round(this.grid[0].length / 2));
                this.doorLocationsInRoom.put("north",posistion);
                this.grid[0][Math.round(this.grid[0].length / 2)] = "E";
            } else if (exits.equals("south")) {
                Posistion posistion = new Posistion(this.grid.length - 1,Math.round(this.grid[0].length / 2));
                this.doorLocationsInRoom.put("south",posistion);
                this.grid[this.grid.length - 1][Math.round(this.grid[0].length / 2)] = "E";
            } else if (exits.equals("west")) {
                Posistion posistion = new Posistion(Math.round(this.grid.length / 2),0);
                this.doorLocationsInRoom.put("west",posistion);
                this.grid[Math.round(this.grid.length / 2)][0] = "E";
            } else if (exits.equals("east")) {
                Posistion posistion = new Posistion(Math.round(this.grid.length / 2),this.grid.length - 1);
                this.doorLocationsInRoom.put("east",posistion);
                this.grid[Math.round(this.grid.length / 2)][this.grid.length - 1] = "E";

            }
        }
    }

    public void moveObject(PlaceableObject object, Command command) {
        if (!(command.hasSecondWord())) {

        }
    }

    public String getGrid() {
        String print = "";
        for (int y = 0; y < this.grid.length; y++) {
            if (y == 0) {
                print += "\n" + verticalLine() + "\n";
            }
            for (int x = 0; x < this.grid[y].length; x++) {
                if (x == 0) {
                    print += "|";
                }
                if (this.grid[y][x] == null) {
                    print += " " + " " + " |";
                } else {
                    print += " " + this.grid[y][x] + " |";
                }
            }
            print += "\n" + verticalLine() + "\n";
        }
        return print;
    }

    private String verticalLine() {
        String line = "";
        for (int i = 0; i < this.grid[0].length; i++) {
            line += "----";
        }
        return line + "-";
    }

}
