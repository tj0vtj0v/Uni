package thd.gameobjects.base;

/**
 * Interface for all ObjectBlockImages that appear according to methodReturn.
 *
 * @param <T> MainCharacterBlockImages Datatype to check for activation.
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
