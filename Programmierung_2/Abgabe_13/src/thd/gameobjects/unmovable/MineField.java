package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.MainCharacterImpl;

public class MineField extends GameObject implements ShiftableGameObject, ActivatableGameObject<GameObject> {
    /**
     * Crates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager GamePlayManager to manage the game actions.
     */
    public MineField(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        blockImage = "";

        for (int i = 0; i <= gamePlayManager.currentLevel().minesPerMinefield; i++) {
            double x = random.nextGaussian(0, GameView.HEIGHT / 4f) + position.getX();
            double y = random.nextGaussian(0, GameView.HEIGHT / 4f) + position.getY();
            gamePlayManager.spawnGameObject(new Mine(gameView, gamePlayManager, Direction.DOWN, new Position(x, y)));
        }

        gamePlayManager.destroyGameObject(this);
    }

    @Override
    public boolean tryToActivate(GameObject info) {
        MainCharacterImpl infoObject = (MainCharacterImpl) info;

        return (infoObject).getPosition().verticalDistance(this.position) <= DEFAULT_SPAWN_DISTANCE * 2;
    }

    @Override
    public void addToCanvas() {
    }
}
