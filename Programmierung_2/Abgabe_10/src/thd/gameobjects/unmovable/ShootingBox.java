package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.resources.ObjectBlockImages;
import thd.gameobjects.movable.Bullet;
import thd.gameobjects.movable.Grenade;
import thd.gameobjects.movable.MainCharacterImpl;

import java.util.Random;


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
        distanceToBackground = 100;
        hitTolerance = 2;

        size = 3;
        rotation = 0;
        width = generateWidthFromBlockImage() * size;
        height = generateHeightFromBlockImage() * size;
        hitBoxOffsets(6, 6, -12, -24);
    }

    private void ruin() {
        if (currentState == State.NORMAL) {
            currentState = State.RUINED;

            gamePlayManager.addScorePoints(-1);
            gamePlayManager.spawnGameObject(new Explosion(gameView, gamePlayManager, direction, new Position(position.getX() + 12, position.getY() + 45)));

            if (this.direction == Direction.LEFT) {
                blockImage = currentState.display;
            } else {
                blockImage = mirrorBlockImage(currentState.display);
            }

            position.down(32);
            distanceToBackground = 50;
            width = 0;
            height = 0;
            hitBoxOffsets(0, 0, 0, 0);
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (currentState == State.NORMAL) {
            if (gamePlayManager.mainCharacterYCoordinate() < position.getY()) {
                ruin();
            } else if (gameView.timer(new Random(System.currentTimeMillis()).nextInt(1000, 2000), this)) {
                gamePlayManager.spawnGameObject(new Bullet(gameView, gamePlayManager, direction.opposite(), position, this));
            }
        }
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= GameView.HEIGHT * 1.5;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Bullet && ((Bullet) other).creator != this) {
            hitTolerance--;
        } else if (other instanceof Explosion) {
            hitTolerance = 0;
        }

        if (hitTolerance <= 0) {
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
