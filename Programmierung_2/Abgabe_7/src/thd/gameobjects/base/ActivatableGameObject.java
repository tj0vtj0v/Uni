package thd.gameobjects.base;

/**
 * Interface for all Objects that appear according to methodReturn.
 */
public interface ActivatableGameObject {
    /**
     * determines if an object should spawn.
     *
     * @param info Object to work with.
     * @return rather object should spawn or not.
     */
    boolean tryToActivate(Object info);
}
