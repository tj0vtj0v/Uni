package thd.game.managers;

import thd.game.utilities.GameView;
import thd.game.utilities.World;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.*;
import thd.gameobjects.movable.*;

import static java.lang.Math.max;

class GameWorldManager extends GamePlayManager {
    private static final int VISIBLE_COLUMNS = 32;
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
                        spawnGameObject(new EnemyMortar(gameView, this, located, position));
                        break;
                    case 'G':
                        spawnGameObject(new EnemyGunner(gameView, this, located, position, collidingGameObjectsForPathDecision));
                        break;
                    case 'H':
                        spawnGameObject(new Humvee(gameView, this, located, position));
                        break;
                    case 'F':
                        spawnGameObject(new Moped(gameView, this, located, position));
                        break;
                    case 'B':
                        spawnPathBlockingGameObject(new ShootingBox(gameView, this, located, position));
                        break;
                    case 'T':
                        spawnPathBlockingGameObject(new Tree(gameView, this, located, position));
                        break;
                    case 'S':
                        spawnPathBlockingGameObject(new Stone(gameView, this, located, position));
                        break;
                    case 'R':
                        spawnPathBlockingGameObject(new Rock(gameView, this, located, position));
                        break;
                    case 'A':
                        spawnGameObject(new AmmoBox(gameView, this, located, position));
                        break;
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                        spawnPathBlockingGameObject(new Wall(gameView, this, located, position, tile - 47));
                }
            }
        }
    }
}
