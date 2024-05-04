package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;

/**
 * Representation of the in-game-object 'EnemyMortar'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class EnemyMortar extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private final String mortarBlockImage;

    /**
     * Creates EnemyMortar with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public EnemyMortar(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (location == Direction.RIGHT) {
            blockImage = EnemyMortarBlockImages.LOADING;
            mortarBlockImage = ObjectBlockImages.MORTAR;
        } else {
            blockImage = mirrorBlockImage(EnemyMortarBlockImages.LOADING);
            mortarBlockImage = mirrorBlockImage(ObjectBlockImages.MORTAR);
        }
        distanceToBackground = 100;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 3, -6, -18);
    }

    private void shoot() {
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, direction, getPosition(), this));
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet || other instanceof Explosion) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addScorePoints(-1);
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (gameView.timer(3300, this)) {
            shoot();
        } else if (gameView.timer(4000, this)) {
            shoot();
        }
    }

    @Override
    public void addToCanvas() {
        double mortarXOffset = direction == Direction.RIGHT ? position.getX() - 18 : position.getX() + 27;
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(mortarBlockImage, mortarXOffset, position.getY() + 21, size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Mortar: %s".formatted(position);
    }
}
