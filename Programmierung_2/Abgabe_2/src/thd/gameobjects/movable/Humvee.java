package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

import java.awt.*;

public class Humvee {
    final GameView gameView;
    final Position position;
    final double speedInPixel;
    double rotation;
    final double size;
    final double width;
    final double height;

    public Humvee(GameView gameView) {
        this.gameView = gameView;

        size = 30;
        position = new Position(0, GameView.HEIGHT / 2f);
        rotation = 0;
        width = 150;
        height = 33;

        speedInPixel = 5;
    }

    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 1", position.getX(), position.getY(), size, true, Color.YELLOW, rotation);
    }

    public void updatePosition() {
        position.right(speedInPixel);
        rotation++;
    }

    @Override
    public String toString() {
        return "Humvee: %s".formatted(position);
    }
}
