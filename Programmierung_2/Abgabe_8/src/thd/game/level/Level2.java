package thd.game.level;

public class Level2 extends Level {
    public Level2() {
        name = "Level 2";
        number = 2;
        worldString = World.LEVEL_1;
        worldOffsetLines = Math.max(worldString.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        mainCharacterMovementSpeed = 5;
    }
}
