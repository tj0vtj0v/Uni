package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility Class for permanent data saving.
 */
public final class FileAccess {
    private static final Path WICHTEL_GAME_FILE = Path.of(System.getProperty("user.home")).resolve("wichtelgame.txt");

    /**
     * Updates difficulty to the file.
     * Ends the Programm in case of a fail.
     *
     * @param difficulty difficulty to write to Disk.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            Files.writeString(WICHTEL_GAME_FILE, difficulty.name());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the difficulty from the disk.
     *
     * @return read difficulty or Standard in case of an error.
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String lines = Files.readString(WICHTEL_GAME_FILE, StandardCharsets.UTF_8);
            return Difficulty.valueOf(lines);
        } catch (IOException | IllegalArgumentException e) {
            writeDifficultyToDisc(Difficulty.STANDARD);
            return Difficulty.STANDARD;
        }
    }
}
