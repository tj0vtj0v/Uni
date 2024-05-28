package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.movable.MortarMovementPattern;
import thd.gameobjects.resources.EnemyMortarBlockImages;
import thd.gameobjects.resources.ObjectBlockImages;

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
            blockImage = currentState.display;
            mortarBlockImage = ObjectBlockImages.MORTAR;
            mortarXOffset = MORTAR_X_OFFSET_IF_RIGHT;
        } else {
            blockImage = mirrorBlockImage(currentState.display);
            mortarBlockImage = mirrorBlockImage(ObjectBlockImages.MORTAR);
            mortarXOffset = MORTAR_X_OFFSET_IF_LEFT;
        }
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * 1, size * 1, size * -2, size * -2);

        random.setSeed(hashCode());
    }

    private void shoot() {
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, new MortarMovementPattern(direction.opposite(), position)));
    }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % State.values().length;
        currentState = State.values()[nextState];
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
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

        if (currentState == State.WAITING & (gameView.timer(random.nextInt(MORTAR_START_WAITING_TIME, MORTAR_END_WAITING_TIME), this))) {
            switchToNextState();

            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
                hitBoxOffsets(size * 6, size * 1, size * -2, size * -2);
            }
        } else if (currentState == State.LOADING && gameView.timer(gamePlayManager.currentLevel().mortarLoadingTime, this)) {
            switchToNextState();

            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
                hitBoxOffsets(size * 1, size * 1, size * -2, size * -2);
            }
        } else if (currentState == State.SHOOTING && gameView.timer(MORTAR_SHOOTING_TIME, this)) {
            switchToNextState();
            shoot();

            if (direction == Direction.RIGHT) {
                position.left(currentState.leftShiftIfRight * size);
                mortarXOffset += currentState.leftShiftIfRight * size;
            }
        }


        if (direction == Direction.RIGHT) {
            blockImage = currentState.display;
        } else {
            blockImage = mirrorBlockImage(currentState.display);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(mortarBlockImage, position.getX() + mortarXOffset, position.getY() + MORTAR_Y_OFFSET, size, rotation);
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
