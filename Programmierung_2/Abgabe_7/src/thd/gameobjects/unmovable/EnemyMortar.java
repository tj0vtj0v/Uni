package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;

/**
 * Representation of the in-game-object 'EnemyMortar'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 1 {@link Bullet}
 * BlockImage
 */
public class EnemyMortar extends CollidingGameObject {
    private String mortarBlockImage;

    /**
     * Creates EnemyMortar with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public EnemyMortar(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (location == Direction.RIGHT) {
            blockImage = CharacterBlockImages.Enemy.Mortar.LOADING;
            mortarBlockImage = ObjectBlockImages.MORTAR;
        } else {
            blockImage = mirrorBlockImage(CharacterBlockImages.Enemy.Mortar.LOADING);
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
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, location, getPosition(), this));
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addScorePoints(-1);
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(320, this)) {
            shoot();
        } else if (gameView.timer(400, this)) {
            shoot();
        }
    }

    @Override
    public void addToCanvas() {
        double mortarXOffset = location == Direction.RIGHT ? position.getX() - 18 : position.getX() + 27;
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(mortarBlockImage, mortarXOffset, position.getY() + 21, size, rotation);
    }

    @Override
    public String toString() {
        return "Enemy Mortar: %s".formatted(position);
    }
}
