package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.GrenadeMovementPattern;
import thd.gameobjects.movable.Vehicle;
import thd.gameobjects.resources.MovingCharacterBlockImages;
import thd.gameobjects.unmovable.Explosion;

import java.util.List;


/**
 * Creates an active moving character.
 */
public abstract class MovingCharacter extends CollidingGameObject {

    protected enum Animation {FRAME_1, FRAME_2, FRAME_3, FRAME_4}

    private final List<CollidingGameObject> collidingGameObjectsForPathDecision;
    protected int shotCooldownInMilliseconds;
    protected boolean moved;
    private Animation currentFrame;
    protected final String[][] animationFrames;

    /**
     * Creates an instance of a moving Character as the main character or an enemy.
     *
     * @param gameView                            gameView where the Object is displayed in.
     * @param gamePlayManager                     manager for the in-game logic.
     * @param facing                              Stores information about movement direction.
     * @param position                            Position from which to start movement.
     * @param collidingGameObjectsForPathDecision list of object which are path blockers.
     */
    protected MovingCharacter(GameView gameView, GamePlayManager gamePlayManager, Direction facing, Position position, List<CollidingGameObject> collidingGameObjectsForPathDecision) {
        super(gameView, gamePlayManager, facing, position);
        this.collidingGameObjectsForPathDecision = collidingGameObjectsForPathDecision;

        moved = false;
        currentFrame = Animation.FRAME_1;
        animationFrames = new String[Direction.values().length][Animation.values().length];
        fillAnimationFrames();
    }

    private void fillAnimationFrames() {
        animationFrames[0][0] = MovingCharacterBlockImages.LEFT_1;
        animationFrames[0][1] = MovingCharacterBlockImages.LEFT_2;
        animationFrames[0][2] = MovingCharacterBlockImages.LEFT_3;
        animationFrames[0][3] = MovingCharacterBlockImages.LEFT_2;
        animationFrames[1][0] = MovingCharacterBlockImages.DOWN_1;
        animationFrames[1][1] = MovingCharacterBlockImages.DOWN_2;
        animationFrames[1][2] = MovingCharacterBlockImages.DOWN_1;
        animationFrames[1][3] = MovingCharacterBlockImages.DOWN_3;
        animationFrames[2][0] = MovingCharacterBlockImages.UP_RIGHT_1;
        animationFrames[2][1] = MovingCharacterBlockImages.UP_RIGHT_2;
        animationFrames[2][2] = MovingCharacterBlockImages.UP_RIGHT_3;
        animationFrames[2][3] = MovingCharacterBlockImages.UP_RIGHT_2;
        animationFrames[3][0] = MovingCharacterBlockImages.UP_LEFT_1;
        animationFrames[3][1] = MovingCharacterBlockImages.UP_LEFT_2;
        animationFrames[3][2] = MovingCharacterBlockImages.UP_LEFT_3;
        animationFrames[3][3] = MovingCharacterBlockImages.UP_LEFT_2;
        animationFrames[4][0] = MovingCharacterBlockImages.DOWN_RIGHT_1;
        animationFrames[4][1] = MovingCharacterBlockImages.DOWN_RIGHT_2;
        animationFrames[4][2] = MovingCharacterBlockImages.DOWN_RIGHT_3;
        animationFrames[4][3] = MovingCharacterBlockImages.DOWN_RIGHT_2;
        animationFrames[5][0] = MovingCharacterBlockImages.DOWN_LEFT_1;
        animationFrames[5][1] = MovingCharacterBlockImages.DOWN_LEFT_2;
        animationFrames[5][2] = MovingCharacterBlockImages.DOWN_LEFT_3;
        animationFrames[5][3] = MovingCharacterBlockImages.DOWN_LEFT_2;
        animationFrames[6][0] = MovingCharacterBlockImages.UP_1;
        animationFrames[6][1] = MovingCharacterBlockImages.UP_2;
        animationFrames[6][2] = MovingCharacterBlockImages.UP_1;
        animationFrames[6][3] = MovingCharacterBlockImages.UP_3;
        animationFrames[7][0] = MovingCharacterBlockImages.RIGHT_1;
        animationFrames[7][1] = MovingCharacterBlockImages.RIGHT_2;
        animationFrames[7][2] = MovingCharacterBlockImages.RIGHT_3;
        animationFrames[7][3] = MovingCharacterBlockImages.RIGHT_2;
    }

    private void switchToNextState() {
        int nextState = (currentFrame.ordinal() + 1) % Animation.values().length;
        currentFrame = Animation.values()[nextState];
    }

    private void recalculateHitBox() {
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * 7, size * 2, size * -12, size * -8);
    }


    protected void updateAnimation() {
        direction = direction == null ? Direction.DOWN : direction;

        if (moved) {
            if (gameView.timer(ANIMATION_SPEED, this)) {
                switchToNextState();
            }
        } else {
            currentFrame = Animation.FRAME_1;
        }

        blockImage = animationFrames[direction.ordinal()][currentFrame.ordinal()];
        recalculateHitBox();
    }

    public void throwGrenade() {
        gamePlayManager.spawnGameObject(new Grenade(gameView, gamePlayManager, new GrenadeMovementPattern(direction, position)));
    }

    /**
     * Creates a Bullet-Object flying down.
     */
    public void shoot() {
        direction = direction == null ? Direction.DOWN : direction;

        double bulletXOffset = 0;
        double bulletYOffset = 0;
        switch (direction) {
            case LEFT, UP_LEFT:
                bulletXOffset = size * 0;
                bulletYOffset = size * 7;
                break;
            case DOWN:
                bulletXOffset = size * 2;
                bulletYOffset = size * 12;
                break;
            case UP_RIGHT:
                bulletXOffset = size * 12;
                bulletYOffset = size * 5;
                break;
            case DOWN_RIGHT:
                bulletXOffset = size * 8;
                bulletYOffset = size * 9;
                break;
            case DOWN_LEFT:
                bulletXOffset = size * 0;
                bulletYOffset = size * 9;
                break;
            case UP:
                bulletXOffset = size * 6;
                bulletYOffset = size * 2;
                break;
            case RIGHT:
                bulletXOffset = size * 16;
                bulletYOffset = size * 7;
        }

        if (gameView.timer(shotCooldownInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, direction, new Position(position.getX() + bulletXOffset, position.getY() + bulletYOffset), this));
        }
    }

    protected boolean fatallyHit(CollidingGameObject other) {
        return other instanceof Bullet && ((Bullet) other).getCreator() != this || other instanceof Explosion || other instanceof Vehicle;
    }

    @Override
    protected boolean pathIsBlocked() {
        for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
            if (collidesWith(collidingGameObject)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }
}
