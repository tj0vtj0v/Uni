package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.MainCharacterImpl;

/**
 * Representation of the in-game-object 'Wall'.
 * <p>
 * unmoving
 * indestructible
 * blocks {@link Bullet}
 * BlockImage
 */
public class Wall extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {

    /**
     * Creates Wall with gameView window of presence.
     *
     * @param gameView        window in which it has to be displayed.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param location        Stores positional information.
     * @param position        Position where to be located.
     * @param segments        amount of segments of the wall.
     */
    public Wall(GameView gameView, GamePlayManager gamePlayManager, Direction location, Position position, int segments) {
        super(gameView, gamePlayManager, location, position);

        blockImage = new ObjectBlockImages().wall(segments);
        distanceToBackground = LAYER_2;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * 5, size * 1, size * -11, size * -6);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Wall: %s".formatted(position);
    }
}
