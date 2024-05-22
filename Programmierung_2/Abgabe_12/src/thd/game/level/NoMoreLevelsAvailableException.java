package thd.game.level;

/**
 * Exception if there are no further Level.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * Creates the Exception.
     *
     * @param message message that is displayed.
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
