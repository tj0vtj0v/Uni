package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.unmovable.Explosion;

/**
 * Representation of the in-game-object 'Bullet'.
 * <p>
 * initial influenced parabolic movement
 * indestructible
 * BlockImage
 */
public class Grenade extends GameObject implements ShiftableGameObject {
    private final ParabolicMovementPattern movementPattern;

    /**
     * Crates a new Grenade.
     *
     * @param gameView        GameView to show the grenade on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param originLocation  Direction in which the bullet should travel.
     * @param position        Position from which to start movement.
     */
    public Grenade(GameView gameView, GamePlayManager gamePlayManager, Direction originLocation, Position position) {
        super(gameView, gamePlayManager);

        blockImage = ObjectBlockImages.MORTAR_GRENADE;
        distanceToBackground = LAYER_5;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;

        speedInPixel = gamePlayManager.currentLevel().grenadeSpeedInPixel;

        movementPattern = new ParabolicMovementPattern(originLocation.opposite(), position);
        this.position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
        if (position.equals(targetPosition)) {
            try {
                targetPosition.updateCoordinates(movementPattern.nextTargetPosition(getPosition()));
            } catch (ExplosionCountdownExpiredException e) {
                gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, Direction.DOWN, position));
                gamePlayManager.destroyGameObject(this);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }
}
