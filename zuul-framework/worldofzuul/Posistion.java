package worldofzuul;

public class Posistion {
    int y,x;
    public Posistion(int y,int x){
        this.y=y;
        this.x=x;
    }

    public void updatePosistion(int y, int x){
        this.y=y;
        this.x=x;
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
        return "Posistion{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
