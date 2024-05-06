package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;


/**
 * Representation of the in-game-object 'ShootingBox'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 2 {@link Bullet}
 * BlockImage
 */
public class ShootingBox extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private int hitTolerance;

    /**
     * Creates ShootingBox with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public ShootingBox(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (this.direction == Direction.LEFT) {
            blockImage = ObjectBlockImages.SHOOTING_BOX_LEFT;
        } else {
            blockImage = mirrorBlockImage(ObjectBlockImages.SHOOTING_BOX_LEFT);
        }
        distanceToBackground = 100;
        hitTolerance = 2;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT * 1.5;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet) {
            hitTolerance--;
        } else if (other instanceof Explosion) {
            hitTolerance = 0;
        }

        if (hitTolerance <= 0) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.addScorePoints(-1);
        }
    }

    @Override
    public String toString() {
        return "ShootingBox: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }
}
