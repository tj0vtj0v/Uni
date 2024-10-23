package thd.game.managers;

/**
 * Exception for game management.
 */
public class NoRemainingMenException extends Exception{
    /**
     * Creates a instance of the exception.
     */
    NoRemainingMenException(){
        super("No men left");
    }
}
