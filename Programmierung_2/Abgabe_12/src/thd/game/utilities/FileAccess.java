package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility Class for permanent data saving.
 */
public final class FileAccess {
    private static final Path WICHTEL_GAME_FILE = Path.of(System.getProperty("user.home")).resolve("wichtelgame.txt");

    /**
     * Resets the file.
     * Ends the Programm in case of a fail.
     */
    public static void writeDefaultToDisc() {
        try {
            Files.writeString(WICHTEL_GAME_FILE, Difficulty.STANDARD.name()+"\n"+0);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Updates difficulty to the file.
     * Ends the Programm in case of a fail.
     *
     * @param difficulty difficulty to write to Disk.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            int highScore = readHighScoreFromDisc();
            Files.writeString(WICHTEL_GAME_FILE, difficulty.name()+"\n"+highScore);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Updates highScore to the file.
     * Ends the Programm in case of a fail.
     *
     * @param highScore highScore to write to Disk.
     */
    public static void writeHighScoreToDisc(int highScore) {
        try {
            Difficulty difficulty = readDifficultyFromDisc();
            Files.writeString(WICHTEL_GAME_FILE, difficulty.name()+"\n"+highScore);
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
            List<String> lines = Files.readAllLines(WICHTEL_GAME_FILE, StandardCharsets.UTF_8);
            return Difficulty.valueOf(lines.get(0));
        } catch (IOException | IllegalArgumentException | IndexOutOfBoundsException e) {
            writeDifficultyToDisc(Difficulty.STANDARD);
            writeHighScoreToDisc(0);
            return Difficulty.STANDARD;
        }
    }

    /**
     * Reads the highScore from the disk.
     *
     * @return read highScore or 0 in case of an error.
     */
    public static int readHighScoreFromDisc() {
        try {
            List<String> lines = Files.readAllLines(WICHTEL_GAME_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(lines.get(1));
        } catch (IOException | IllegalArgumentException | IndexOutOfBoundsException e) {
            writeDefaultToDisc();
            return 0;
        }
    }
}
