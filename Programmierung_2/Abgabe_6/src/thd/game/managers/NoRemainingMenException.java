package thd.game.managers;

public class NoRemainingMenException extends Exception{
    public NoRemainingMenException(){
        super("No men left");
    }
}
