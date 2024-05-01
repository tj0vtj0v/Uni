package thd.game.level;

public class Level1 extends Level {
    public Level1() {
        name = "Level 1";
        number = 1;
        worldString = World.LEVEL_1;
        worldOffsetLines = Math.max(worldString.split("\n").length - 32, 0);
        worldOffsetColumns = 10;
    }
}
