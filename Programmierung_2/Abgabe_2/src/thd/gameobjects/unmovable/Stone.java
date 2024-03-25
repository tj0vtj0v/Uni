package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

import java.awt.*;

public class Stone {
    final GameView gameView;
    final Position position;
    double rotation;
    final double size;
    final double width;
    final double height;

    public Stone(GameView gameView) {
        this.gameView = gameView;

        size = 30;
        rotation = 0;
        width = 150;
        height = 33;
        position = new Position(GameView.WIDTH - width, 0);
    }

    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 3", position.getX() + 3, position.getY() - 5, size, true, Color.YELLOW, rotation);
    }

    @Override
    public String toString() {
        return "Tree: %s".formatted(position);
    }
}
