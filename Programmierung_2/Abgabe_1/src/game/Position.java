package game;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this(0,0);
    }

    public Position(Position otherPosition) {
        x = otherPosition.getX();
        y = otherPosition.getY();
    }

    public void updateCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void updateCoordinates(Position otherPosition) {
        x = otherPosition.getX();
        y = otherPosition.getY();
    }

    public void left() {
        x--;
    }

    public void left(double pixel) {
        x -= pixel;
    }

    public void right() {
        x++;
    }

    public void right(double pixel) {
        x += pixel;
    }

    public void up() {
        y--;
    }

    public void up(double pixel) {
        y -= pixel;
    }

    public void down() {
        y++;
    }

    public void down(double pixel) {
        y += pixel;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position (%d, %d)".formatted((int) Math.round(x), (int) Math.round(y));
    }
}
