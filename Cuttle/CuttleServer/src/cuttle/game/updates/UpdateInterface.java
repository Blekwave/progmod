package cuttle.game.updates;

/**
 * Interface to be implemented by objects which can provide an update container
 * to the ServerAdapter.
 */
public interface UpdateInterface {
    /**
     * Method which generates an UpdateContainer which can generate the update
     * JSONs for the player and the opponent.
     *
     * @return UpdateContainer for the update.
     */
    UpdateContainer buildUpdate();
}
