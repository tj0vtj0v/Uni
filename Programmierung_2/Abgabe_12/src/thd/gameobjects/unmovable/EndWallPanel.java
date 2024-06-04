package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.resources.ObjectBlockImages;

/**
 * Represents the side panel of the end wall.
 */
public class EndWallPanel extends CollidingGameObject implements ShiftableGameObject {


    /**
     * Represents the side panel of the end wall.
     *
     * @param gameView        window to be displayed on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     * @param facing          direction on which side the panel is at.
     * @param position        where the wall is placed.
     */
    public EndWallPanel(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position) {
        super(gameView, gamePlayManager, facing, position);


        blockImage = " ";
        size = BLOCK_IMAGE_SIZE;
        distanceToBackground = BACKGROUND;
        rotation = 0;
        this.width = (GameView.WIDTH - ObjectBlockImages.OPEN_DOOR[0].length()) / 2f;
        this.height = ObjectBlockImages.OPEN_DOOR.length - EndWall.HIT_BOX_HEIGHT_OFFSET;

        if (facing == Direction.LEFT) {
            this.position.right((GameView.WIDTH + ObjectBlockImages.CLOSE_DOOR.length * size) / 2f);
        }

        hitBoxOffsets(0, 0, 0, -12 * size);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }
}
