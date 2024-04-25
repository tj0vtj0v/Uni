package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;


/**
 * Representation of the in-game-object 'ShootingBox'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 2 {@link Bullet}
 * BlockImage
 */
public class ShootingBox extends CollidingGameObject {
    private int hitTolerance = 2;

    /**
     * Creates ShootingBox with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param hitTolerance
     */
    public ShootingBox(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);

        blockImage = ObjectBlockImages.SHOOTING_BOX_LEFT;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 3, -6, -18);

        position.updateCoordinates(50, 100);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;

            if (hitTolerance <= 0) {
                gamePlayManager.destroyGameObject(this);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "ShootingBox: %s".formatted(position);
    }
}
