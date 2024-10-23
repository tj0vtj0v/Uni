package game;

import java.awt.*;

public class ScoreBoard {
    GameView gameView;
    Position position;
    double speedInPixel;
    double rotation;
    double size;
    double width;
    double height;

    public ScoreBoard(GameView gameView) {
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
        return "ScoreBoard: %s".formatted(position);
    }
}
