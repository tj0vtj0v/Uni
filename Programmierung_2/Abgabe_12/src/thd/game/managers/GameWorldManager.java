package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.EnemyGunner;
import thd.gameobjects.movable.Humvee;
import thd.gameobjects.movable.MainCharacterImpl;
import thd.gameobjects.movable.Moped;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {
    private final List<GameObject> activatableGameObjects;

    protected GameWorldManager(GameView gameView) {
        super(gameView);
        activatableGameObjects = new LinkedList<>();
    }

    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
        clearListsForPathDecisionsInGameObjects();
    }

    private void spawnGameObjects() {
        BackgroundPixels backgroundPixels = new BackgroundPixels(gameView, this);
        addToShiftableGameObjectsIfShiftable(backgroundPixels);
        spawnGameObject(backgroundPixels);

        spawnGameObject(new ScoreBoard(gameView, this));

        overlay = new Overlay(gameView, this);
        spawnGameObject(overlay);
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = level.world.split("\n");
        char tile;
        Position position;
        Direction located;

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int tileIndex = 0; tileIndex < lines[lineIndex].length(); tileIndex++) {
                tile = lines[lineIndex].charAt(tileIndex);

                located = Character.isUpperCase(tile) ? Direction.RIGHT : Direction.LEFT;
                position = new Position((tileIndex - level.worldOffsetColumns) * level.worldScale, (lineIndex - level.worldOffsetLines) * level.worldScale);


                switch (Character.toUpperCase(tile)) {
                    case 'I':
                        mainCharacter = new MainCharacterImpl(gameView, this, Direction.DOWN, position, collidingGameObjectsForPathDecision);
                        spawnGameObject(mainCharacter);
                        break;
                    case 'E':
                        addActivatableGameObject(new EndWall(gameView, this, located, position));
                        addActivatableGameObject(new EndWallPanel(gameView, this, located, position));
                        addActivatableGameObject(new EndWallPanel(gameView, this, located.opposite(), position));
                        break;
                    case 'M':
                        addActivatableGameObject(new EnemyMortar(gameView, this, located, position));
                        break;
                    case 'D':
                        addActivatableGameObject(new Spawner(gameView, this, located, position, collidingGameObjectsForPathDecision));
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

    private void clearListsForPathDecisionsInGameObjects() {
        collidingGameObjectsForPathDecision.clear();
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();

        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();

            if (gameObject instanceof EnemyMortar enemyMortar) {
                if (enemyMortar.tryToActivate(mainCharacter)) {
                    spawnGameObject(enemyMortar);
                    iterator.remove();
                }

            } else if (gameObject instanceof EndWallPanel endWallPanel) {
                spawnPathBlockingGameObject(endWallPanel);
                iterator.remove();
            } else if (gameObject instanceof EndWall endWall) {
                if (endWall.tryToActivate(mainCharacter)) {
                    spawnPathBlockingGameObject(endWall);
                    iterator.remove();
                }
            } else if (gameObject instanceof EnemyGunner enemyGunner) {
                if (enemyGunner.tryToActivate(mainCharacter)) {
                    spawnGameObject(enemyGunner);
                    iterator.remove();
                }
            } else if (gameObject instanceof Spawner spawner) {
                if (spawner.tryToActivate(mainCharacter)) {
                    spawnGameObject(spawner);
                    iterator.remove();
                }
            } else if (gameObject instanceof Humvee humvee) {
                if (humvee.tryToActivate(mainCharacter)) {
                    spawnGameObject(humvee);
                    iterator.remove();
                }
            } else if (gameObject instanceof Moped moped) {
                if (moped.tryToActivate(mainCharacter)) {
                    spawnGameObject(moped);
                    iterator.remove();
                }
            } else if (gameObject instanceof ShootingBox shootingBox) {
                if (shootingBox.tryToActivate(mainCharacter)) {
                    spawnGameObject(shootingBox);
                    iterator.remove();
                }
            } else if (gameObject instanceof Tree tree) {
                if (tree.tryToActivate(mainCharacter)) {
                    spawnPathBlockingGameObject(tree);
                    iterator.remove();
                }
            } else if (gameObject instanceof Stone stone) {
                if (stone.tryToActivate(mainCharacter)) {
                    spawnPathBlockingGameObject(stone);
                    iterator.remove();
                }
            } else if (gameObject instanceof Rock rock) {
                if (rock.tryToActivate(mainCharacter)) {
                    spawnPathBlockingGameObject(rock);
                    iterator.remove();
                }
            } else if (gameObject instanceof AmmoBox ammoBox) {
                if (ammoBox.tryToActivate(mainCharacter)) {
                    spawnGameObject(ammoBox);
                    iterator.remove();
                }
            } else if (gameObject instanceof Wall wall) {
                if (wall.tryToActivate(mainCharacter)) {
                    spawnPathBlockingGameObject(wall);
                    iterator.remove();
                }
            }
        }

    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }
}
