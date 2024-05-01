package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainCharacterImpl;

/**
 * Representation of the in-game-object 'Rock'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Rock extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {

    /**
     * Creates Rock with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     */
    public Rock(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position) {
        super(gameView, gamePlayManager, location, position);

        if (location == Direction.RIGHT) {
            blockImage = ObjectBlockImages.RIGHT_SIDE_ROCK;
        } else {
            blockImage = mirrorBlockImage(ObjectBlockImages.RIGHT_SIDE_ROCK);
        }
        distanceToBackground = 200;

        size = 4;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(3, 6, -6, -18);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Stone: %s".formatted(position);
    }
}
