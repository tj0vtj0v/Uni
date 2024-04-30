package thd.game.managers;

import thd.game.utilities.GameView;
import thd.game.utilities.World;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.*;
import thd.gameobjects.movable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Math.max;

class GameWorldManager extends GamePlayManager {
    private static final int VISIBLE_COLUMNS = 32;
    private final List<ActivatableGameObject> activatableGameObjects;
    private final String world;
    private final int worldOffsetLines;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    GameWorldManager(GameView gameView) {
        super(gameView);
        world = World.LEVEL_1;
        worldOffsetLines = max(world.split("\n").length - VISIBLE_COLUMNS, 0);
        activatableGameObjects = new LinkedList<>();


        scoreBoard = new ScoreBoard(gameView, this);
        spawnGameObject(scoreBoard);

        spawnGameObjectsFromWorldString();
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = world.split("\n"); // ("\\R");
        int scale = GameView.WIDTH / (lines[0].length() - 1);
        char tile;
        Position position;
        Direction located;

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int tileIndex = 0; tileIndex < lines[lineIndex].length(); tileIndex++) {
                tile = lines[lineIndex].charAt(tileIndex);

                located = Character.isUpperCase(tile) ? Direction.RIGHT : Direction.LEFT;
                position = new Position(tileIndex * scale, (lineIndex - worldOffsetLines) * scale);

                switch (Character.toUpperCase(tile)) {
                    case 'I':
                        mainCharacter = new MainCharacterImpl(gameView, this, Direction.DOWN, position, collidingGameObjectsForPathDecision);
                        spawnGameObject(mainCharacter);
                        break;
                    case 'M':
                        addActivatableGameObject(new EnemyMortar(gameView, this, located, position));
                        break;
                    case 'G':
                        addActivatableGameObject(new EnemyGunner(gameView, this, located, position, collidingGameObjectsForPathDecision));
                        break;
                    case 'H':
                        addActivatableGameObject(new Humvee(gameView, this, located, position));
                        break;
                    case 'F':
                        addActivatableGameObject(new Moped(gameView, this, located, position));
                        break;
                    case 'B':
                        addActivatableGameObject(new ShootingBox(gameView, this, located, position));
                        break;
                    case 'T':
                        addActivatableGameObject(new Tree(gameView, this, located, position));
                        break;
                    case 'S':
                        addActivatableGameObject(new Stone(gameView, this, located, position));
                        break;
                    case 'R':
                        addActivatableGameObject(new Rock(gameView, this, located, position));
                        break;
                    case 'A':
                        addActivatableGameObject(new AmmoBox(gameView, this, located, position));
                        break;
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                        addActivatableGameObject(new Wall(gameView, this, located, position, tile - 47));
                }
            }
        }
    }

    private void addActivatableGameObject(ActivatableGameObject activatableGameObject) {
        activatableGameObjects.add(activatableGameObject);
        addToShiftableGameObjectsIfShiftable((GameObject) activatableGameObject);
    }

    private void activateGameObjects() {
        ListIterator<ActivatableGameObject> iterator = activatableGameObjects.listIterator();

        while (iterator.hasNext()) {
            ActivatableGameObject activatableGameObject = iterator.next();

            if (activatableGameObject.tryToActivate(mainCharacter)) {

                if (instanceOfPathCollisionObject(activatableGameObject)) {
                    spawnPathBlockingGameObject((CollidingGameObject) activatableGameObject);
                } else {
                    spawnGameObject((GameObject) activatableGameObject);
                }

                iterator.remove();
            }
        }
    }

    private boolean instanceOfPathCollisionObject(ActivatableGameObject activatableGameObject) {
        return activatableGameObject instanceof Tree
                || activatableGameObject instanceof Stone
                || activatableGameObject instanceof Rock
                || activatableGameObject instanceof Wall;
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }
}
