package thd.game.managers;

/**
 * Exception causing Grenade to explode.
 */
public class ExplosionCountdownExpiredException extends RuntimeException {
    /**
     * Creates the Exception.
     *
     * @param message Text the Exception has to display.
     */
    public ExplosionCountdownExpiredException(String message) {
        super(message);
    }
}
