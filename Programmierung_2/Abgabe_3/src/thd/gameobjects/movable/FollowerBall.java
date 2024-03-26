package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;


/**
 * Framework component for movements.
 * Copied from RandomBall.
 */
public class FollowerBall extends GameObject {
    private final RandomBall followMe;

    /**
     * Creates RandomBall with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     * @param followMe RandomBall which the follower should follow.
     */
    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        this.followMe = followMe;

        size = 50;
        rotation = 0;
        width = 50;
        height = 50;

        speedInPixel = 3;

        position.updateCoordinates(new Position());
        targetPosition.updateCoordinates(followMe.getPosition());
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 2, true, Color.GREEN);
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(followMe.getPosition());
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public String toString() {
        return "Moped: %s".formatted(position);
    }
}
