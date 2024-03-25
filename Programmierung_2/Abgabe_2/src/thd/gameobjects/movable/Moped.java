package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

import java.awt.*;

public class Moped {
    final GameView gameView;
    final Position position;
    final double speedInPixel;
    double rotation;
    final double size;
    final double width;
    final double height;

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
