package thd.game.managers;

public class ExplosionCountdownExpiredException extends RuntimeException {
    public ExplosionCountdownExpiredException() {
        super("Grenade is exploded.");
    }
}
