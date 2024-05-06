package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.EnemyMortarBlockImages;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;

import java.util.Random;

/**
 * Representation of the in-game-object 'EnemyMortarBlockImages'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class EnemyMortar extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private final String mortarBlockImage;
    private double mortarXOffset;
    private State currentState;

    /**
     * Creates EnemyMortarBlockImages with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public EnemyMortar(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);
        currentState = State.WAITING;

        if (location == Direction.RIGHT) {
            instanceBlockImage = currentState.display;
            mortarBlockImage = ObjectBlockImages.MORTAR;
            mortarXOffset = -33;
        } else {
            instanceBlockImage = mirrorBlockImage(currentState.display);
            mortarBlockImage = mirrorBlockImage(ObjectBlockImages.MORTAR);
            mortarXOffset = 27;
        }
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 3, -6, -6);
    }

    private void shoot() {
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, direction, getPosition()));
    }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % State.values().length;
        currentState = State.values()[nextState];
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet || other instanceof Explosion) {
            gamePlayManager.addScorePoints(-1);
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new DeadEnemy(gameView, gamePlayManager, position));
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();

        if (currentState == State.WAITING & gameView.timer(new Random(System.currentTimeMillis()).nextInt(500, 3500), this)) {
            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
                hitBoxOffsets(18, 3, -6, -6);
            }
        } else if (currentState == State.LOADING && gameView.timer(1000, this)) {
            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
                hitBoxOffsets(3, 3, -6, -6);
            }
        } else if (currentState == State.SHOOTING && gameView.timer(1000, this)) {
            shoot();

            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
            }
        }


        if (direction == Direction.RIGHT) {
            instanceBlockImage = currentState.display;
        } else {
            instanceBlockImage = mirrorBlockImage(currentState.display);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(instanceBlockImage, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(mortarBlockImage, position.getX() + mortarXOffset, position.getY() + 21, size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Mortar: %s in state %s with mortar:\n%s\n which has an horizontal offset of %f".formatted(position, currentState, mortarBlockImage, mortarXOffset);
    }

    private enum State {
        WAITING(EnemyMortarBlockImages.NORMAL, 0),
        LOADING(EnemyMortarBlockImages.LOADING, 5),
        SHOOTING(EnemyMortarBlockImages.NORMAL, -5);

        private final String display;
        private final int leftShiftIfRight;

        State(String display, int leftShiftIfRight) {
            this.display = display;
            this.leftShiftIfRight = leftShiftIfRight;
        }
    }
}
