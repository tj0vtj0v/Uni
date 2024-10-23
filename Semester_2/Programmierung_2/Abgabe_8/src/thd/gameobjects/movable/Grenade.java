package thd.gameobjects.movable;

import thd.game.managers.ExplosionCountdownExpiredException;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
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

    private enum State {FLYING, EXPLODED}

    private State currentState;

    /**
     * Crates a new Grenade.
     *
     * @param gameView        GameView to show the grenade on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param originLocation  Direction in which the bullet should travel.
     * @param position        Position from which to start movement.
     * @param creator         Instance which shoots this Bullet.
     */
    public Grenade(GameView gameView, GamePlayManager gamePlayManager, Direction originLocation, Position position, GameObject creator) {
        super(gameView, gamePlayManager);

        blockImage = ObjectBlockImages.MORTAR_GRENADE;
        distanceToBackground = (char) (creator.getDistanceToBackground() + 1);

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;

        speedInPixel = 8;

        currentState = State.FLYING;
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
                currentState = State.EXPLODED;
            }
        }
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case FLYING:
            case EXPLODED:
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }
}
