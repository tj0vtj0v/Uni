package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the whole plot of the game.
 */
public class GamePlayManager extends WorldShiftManager {
    private final GameObjectManager gameObjectManager;
    final List<CollidingGameObject> collidingGameObjectsForPathDecision;

    int lives;
    int points;
    int highScore;
    /**
     * Communicates if the end of the level is reached.
     */
    public boolean endReached;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
        collidingGameObjectsForPathDecision = new ArrayList<>();
        lives = LIVES;
        points = 0;
        highScore = FileAccess.readHighScoreFromDisc();
        endReached = false;
    }

    /**
     * Reduces remaining lives of the main character.
     */
    public void reduceLive() {
        lives--;
    }

    /**
     * Adds points to the current Score and updates highScore if necessary.
     *
     * @param points amount of points to be added to the score.
     */
    public void addScorePoints(int points) {
        if (points <= 0){
            throw new IllegalArgumentException("Points have to be positive and bigger than 0!");
        }
        this.points += points;
    }

    /**
     * Getter for the points collected in this game.
     *
     * @return amount of scored points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter of the Highscore in this run of the game.
     *
     * @return highScore.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Getter of the remaining grenades to the main character.
     *
     * @return remaining grenades.
     */
    public int availableGrenades() {
        return mainCharacter.getAvailableGrenades();
    }

    /**
     * Method to access the current position of the mainCharacter.
     *
     * @return Position of the mainCharacter.
     */
    public Position mainCharacterPosition() {
        return mainCharacter.getPosition();
    }

    /**
     * Getter of the remaining lives of the main character.
     *
     * @return remaining lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Current level for character influence.
     *
     * @return current Level.
     */
    public Level currentLevel() {
        return level;
    }

    boolean enemyExisting() {
        return gameObjectManager.enemyExisting();
    }

    @Override
    public void spawnGameObject(GameObject gameObject) {
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    /**
     * Spawns Object onto {@link GameView} canvas.
     *
     * @param collidingGameObject CollidingGameObject to create.
     */
    void spawnPathBlockingGameObject(CollidingGameObject collidingGameObject) {
        spawnGameObject(collidingGameObject);
        collidingGameObjectsForPathDecision.add(collidingGameObject);
    }

    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
        collidingGameObjectsForPathDecision.remove(gameObject);
    }

    @Override
    protected void destroyAllGameObjects() {
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
    }
}
