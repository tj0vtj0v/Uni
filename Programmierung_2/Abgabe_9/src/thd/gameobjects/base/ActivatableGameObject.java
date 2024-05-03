package thd.gameobjects.base;

/**
 * Interface for all Objects that appear according to methodReturn.
 *
 * @param <T> MainCharacter Datatype to check for activation.
 */
public interface ActivatableGameObject<T> {
    /**
     * determines if an object should spawn.
     *
     * @param info Object to work with.
     * @return rather object should spawn or not.
     */
    boolean tryToActivate(T info);
}
