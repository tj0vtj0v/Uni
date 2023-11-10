package frograce.game;

public class Game {
    private final Track track;
    private final Frog[] frogs;
    private Frog winningFrog;
    private boolean gameOver;

    public Game(String trackName, int distanceInCm, Frog[] frogs) {
        this.frogs = frogs;

        track = new Track(trackName, distanceInCm, frogs.clone());
        gameOver = false;
        winningFrog = null;
    }

    public void nextIteration() {
        shuffleFrogs();
        letFrogsJumpAndCheckForWinner();
    }

    private void shuffleFrogs() {
        for (int i = 0; i < frogs.length; i++) {
            swapFrogs(i, (int) (Math.random() * frogs.length));
        }
    }

    private void swapFrogs(int index1, int index2) {
        Frog saved = frogs[index1];
        frogs[index1] = frogs[index2];
        frogs[index2] = saved;
    }

    private void letFrogsJumpAndCheckForWinner() {
        for (Frog frog : frogs) {
            frog.jump();

            if (frog.getCurrentDistanceInCm() > track.getDistanceInCm()) {
                gameOver = true;
                winningFrog = frog;

                break;
            }
        }
    }

    @Override
    public String toString() {
        return gameOver ? track.toString() + winningFrog.getName() + " hat gewonnen!" : track.toString();
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
