package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;


/**
 * Representation of the in-game-object 'Enemy'.
 * <p>
 * active linear movement
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * png textured
 */
public class EnemyGunner extends GameObject {
    private final RandomMovementPattern movementPattern;

    /**
     * Creates Enemy Gunner with gameView window of presence.
     *
     * @param gameView window in which it has to be displayed.
     */
    public EnemyGunner(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        size = 3;
        rotation = 0;
        width = 0;
        height = 0;

        speedInPixel = 3;

        movementPattern = new RandomMovementPattern();
        position.updateCoordinates(new Position(0, GameView.HEIGHT / 3d));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("main_character/main_downleft_1.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public String toString() {
        return "EnemyGunner: %s".formatted(position);
    }
}
