package worldofzuul;

import java.util.Objects;

public class Position {
    int y,x;
    public Position(int y, int x){
        this.y=y;
        this.x=x;
    }

    public void updatePosition(int y, int x){
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return this.y == position.y && this.x == position.x;
    }
}
