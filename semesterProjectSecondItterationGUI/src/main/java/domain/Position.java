package domain;

public class Position {

    //Class to keep track of an objects position

    private int y, x;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void updatePosition(int y, int x) {
        setY(y);
        setX(x);
    }

    public void updatePosition(Position position) {
        setX(position.getX());
        setY(position.getY());
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Position{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }

    @Override
    public boolean equals(Object o) { //Overrides the equals' method to instead of comparing the datatypes location in the memory to,
        //compare its y position and x position
        if (this == o) { //checks if the object you are trying to compare is the same object
            return true;
        }
        if (!(o instanceof Position)) { //Checks if the object is a position type object
            return false;
        }
        Position position = (Position) o;
        return this.y == position.y && this.x == position.x;
    }
}
