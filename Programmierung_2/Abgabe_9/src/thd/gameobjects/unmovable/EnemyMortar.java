package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.blockImages.EnemyMortarBlockImages;
import thd.gameobjects.blockImages.ObjectBlockImages;
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
    private ShootingState shootingState;

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
        shootingState = ShootingState.WAITING;

        if (location == Direction.RIGHT) {
            blockImage = shootingState.display;
            mortarBlockImage = ObjectBlockImages.MORTAR;
            mortarXOffset = -33;
        } else {
            blockImage = mirrorBlockImage(shootingState.display);
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
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, direction, getPosition(), this));
    }

    private void switchToNextState() {
        int nextState = (shootingState.ordinal() + 1) % ShootingState.values().length;
        shootingState = ShootingState.values()[nextState];
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

        if (shootingState == ShootingState.WAITING & gameView.timer(new Random(System.currentTimeMillis()).nextInt(500, 3500), this)) {
            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(shootingState.leftShiftIfRight * size);
                mortarXOffset += shootingState.leftShiftIfRight * size;
                hitBoxOffsets(18, 3, -6, -6);
            }
        } else if (shootingState == ShootingState.LOADING && gameView.timer(1000, this)) {
            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(shootingState.leftShiftIfRight * size);
                mortarXOffset += shootingState.leftShiftIfRight * size;
                hitBoxOffsets(3, 3, -6, -6);
            }
        } else if (shootingState == ShootingState.SHOOTING && gameView.timer(1000, this)) {
            shoot();

            switchToNextState();
            if (direction == Direction.RIGHT) {
                position.left(shootingState.leftShiftIfRight * size);
                mortarXOffset += shootingState.leftShiftIfRight * size;
            }
        }


        if (direction == Direction.RIGHT) {
            blockImage = shootingState.display;
        } else {
            blockImage = mirrorBlockImage(shootingState.display);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(mortarBlockImage, position.getX() + mortarXOffset, position.getY() + 21, size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Mortar: %s in state %s with mortar:\n%s\n which has an horizontal offset of %f".formatted(position, shootingState, mortarBlockImage, mortarXOffset);
    }

    private enum ShootingState {
        WAITING(EnemyMortarBlockImages.NORMAL, 0),
        LOADING(EnemyMortarBlockImages.LOADING, 5),
        SHOOTING(EnemyMortarBlockImages.NORMAL, -5);

        private final String display;
        private final int leftShiftIfRight;

        ShootingState(String display, int leftShiftIfRight) {
            this.display = display;
            this.leftShiftIfRight = leftShiftIfRight;
        }
    }
}
