package thd.game.level;

public class Level3 extends Level {
    public Level3() {
        name = "Level 3";
        number = 3;
        worldString = World.LEVEL_1;
        worldOffsetLines = Math.max(worldString.split("\n").length - VISIBLE_COLUMNS, 0);
        worldOffsetColumns = 10;
        mainCharacterMovementSpeed = 2;
    }
}
