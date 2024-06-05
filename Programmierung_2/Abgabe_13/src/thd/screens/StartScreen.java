package thd.screens;

import thd.game.level.Difficulty;
import thd.game.utilities.GameView;

/**
 * Represents the Screen showing at gameStart.
 */
public class StartScreen {
    private final GameView gameView;
    private Difficulty selectedDifficulty;

    /**
     * Creates the Screen showing at gameStart.
     *
     * @param gameView window to display on.
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
        this.selectedDifficulty = Difficulty.STANDARD;
    }

    /**
     * Prints the Screen onto the gameView.
     *
     * @param preselectedDifficulty initial difficulty.
     */
    public void showStartScreenWithPreselectedDifficulty(Difficulty preselectedDifficulty) {
        String title = "WHO DARES WINS";
        String description = """
                Step into the combat boots of a fearless commando, dropped behind enemy lines with one mission: to infiltrate, outmaneuver,
                and outgun the enemy forces.

                As the main character, you're not just any soldier – you are the elite, the brave, the unstoppable.

                Prepare to face relentless waves of enemies, dodge a hailstorm of bullets, and overcome deadly obstacles.
                With only your trusty machine gun and a finite supply of grenades, every step you take is a pulse-pounding fight for survival.

                Master the Controls and Dominate the Battlefield:

                      W   - Propel yourself forward into the heart of danger.
                      A   - Strafe left, evading enemy fire and finding the perfect cover.
                      S   - Retreat strategically, regroup, and plan your next move.
                      D   - Strafe right, outmaneuvering your foes with swift precision.
                    Space - Unleash a storm of bullets, mowing down enemies in your path.

                So, soldier, gear up and get ready to prove that you have what it takes... and remember: ONLY WHO DARES WINS!
                """;

        String[] choices = {Difficulty.EASY.name(),Difficulty.STANDARD.name(),Difficulty.HARD.name(),Difficulty.IMPOSSIBLE.name()};
        int difficultyOrdinal = gameView.showStartScreenWithChooseBox(title, description, "Difficulty", choices, preselectedDifficulty.ordinal());
        selectedDifficulty = Difficulty.values()[difficultyOrdinal];
    }

    /**
     * Getter for the Difficulty.
     *
     * @return the selected Difficulty.
     */
    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
