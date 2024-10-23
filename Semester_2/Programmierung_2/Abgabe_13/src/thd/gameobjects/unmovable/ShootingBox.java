package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.ExplodingBullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.resources.ObjectBlockImages;


/**
 * Representation of the in-game-object 'ShootingBox'.
 * <p>
 * unmoving
 * destructible by 1 {@link Grenade} or 2 {@link Bullet}
 * BlockImage
 */
public class ShootingBox extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    private int hitTolerance;
    private State currentState;

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
        currentState = State.NORMAL;

        if (this.direction == Direction.LEFT) {
            blockImage = currentState.display;
        } else {
            blockImage = mirrorBlockImage(currentState.display);
        }
        distanceToBackground = LAYER_2;
        hitTolerance = DEFAULT_SHOOTING_BOX_HIT_TOLERANCE;

        size = BLOCK_IMAGE_SIZE;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(size * 2, size * 2, size * -4, size * -8);

        random.setSeed(hashCode());
    }

    private void ruin() {
        currentState = State.RUINED;

        gamePlayManager.addPoints(200);
        gamePlayManager.spawnGameObject(new DustExplosion(gameView, gamePlayManager, direction, new Position(position.getX() + SHOOTING_BOX_EXPLOSION_X_OFFSET, position.getY() + SHOOTING_BOX_EXPLOSION_Y_OFFSET)));

        if (this.direction == Direction.LEFT) {
            blockImage = currentState.display;
        } else {
            blockImage = mirrorBlockImage(currentState.display);
        }

        position.down(SHOOTING_BOX_RUIN_Y_OFFSET);
        distanceToBackground = LAYER_1;
        width = 0;
        height = 0;
        hitBoxOffsets(0, 0, 0, 0);

    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (currentState == State.NORMAL) {
            if (gamePlayManager.mainCharacterPosition().getY() < position.getY()) {
                ruin();
            } else if (gameView.timer(random.nextInt(SHOOTING_BOX_START_SHOOTING_TIME, SHOOTING_BOX_END_SHOOTING_TIME), this)) {
                gamePlayManager.spawnGameObject(new ExplodingBullet(gameView, gamePlayManager, direction.opposite().addDirection(Direction.DOWN), new Position(position.getX() + SHOOTING_BOX_BULLET_X_OFFSET, position.getY() + SHOOTING_BOX_BULLET_Y_OFFSET), this));
            }
        }
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).getCreator() != this) {
            hitTolerance--;
        } else if (other instanceof Explosion) {
            hitTolerance = 0;
        }

        if (hitTolerance <= 0 && currentState == State.NORMAL) {
            ruin();
        }
    }

    @Override
    public String toString() {
        return "ShootingBox: %s with %d hits left till destruction".formatted(position, hitTolerance);
    }

    private enum State {
        NORMAL(ObjectBlockImages.SHOOTING_BOX_LEFT),
        RUINED(ObjectBlockImages.RUINED_SHOOTING_BOX_LEFT);

        private final String display;

        State(String display) {
            this.display = display;
        }
    }
}
