package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Direction;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.*;
import thd.gameobjects.movable.*;

class GameWorldManager extends GamePlayManager {
    private final String world;

    /**
     * Creates an instance of the GamePlayManager.
     *
     * @param gameView window to manage.
     */
    GameWorldManager(GameView gameView) {
        super(gameView);
        // Capital characters represent right (mostly the original rotation) and non-capitals represent left.
        world = """
                  T     A   \s
                       S   T\s
                   T    T   \s
                    m     M \s
                      T     \s
                   T   I    \s
                            \s
                """;

        scoreBoard = new ScoreBoard(gameView, this);

        spawnGameObject(scoreBoard);

        spawnGameObjectsFromWorldString();
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = world.split("\n"); // ("\\R");
        int scale = GameView.WIDTH / lines[0].length();
        char tile;
        Position position;
        Direction located;

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int tileIndex = 0; tileIndex < lines[lineIndex].length(); tileIndex++) {
                tile = lines[lineIndex].charAt(tileIndex);

                located = Character.isUpperCase(tile) ? Direction.RIGHT : Direction.LEFT;
                position = new Position(tileIndex * scale, lineIndex * scale);

                switch (Character.toUpperCase(tile)) {
                    case 'I':
                        mainCharacter = new MainCharacterImpl(gameView, this, Direction.DOWN, position, collidingGameObjectsForPathDecision);
                        spawnGameObject(mainCharacter);
                        break;
                    case 'M':
                        spawnGameObject(new EnemyMortar(gameView, this, located, position));
                        break;
                    case 'T':
                        spawnPathBlockingGameObject(new Tree(gameView, this, located, position));
                        break;
                    case 'S':
                        spawnPathBlockingGameObject(new Stone(gameView, this, located, position));
                        break;
                }
            }
        }
    }
}
