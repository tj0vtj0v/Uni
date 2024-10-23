package game;

import java.awt.*;

public class Moped {
    GameView gameView;
    Position position;
    double speedInPixel;
    double rotation;
    double size;
    double width;
    double height;

    public Moped(GameView gameView) {
        this.gameView = gameView;

        size = 30;
        position = new Position(1100, 650);
        rotation = 0;
        width = 150;
        height = 33;

        speedInPixel = 2;
    }

    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 150, 35, 0, true, Color.GREEN);
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 150, 35, 5, false, Color.WHITE);
        gameView.addTextToCanvas("Objekt 2", position.getX() + 3, position.getY() - 3, size, true, Color.BLUE, rotation);
    }

    public void updatePosition() {
        position.left(speedInPixel);
    }

    @Override
    public String toString() {
        return "Moped: %s".formatted(position);
    }
}
