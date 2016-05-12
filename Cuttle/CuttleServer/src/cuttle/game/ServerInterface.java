package cuttle.game;

import org.json.JSONObject;

/**
 * Interface to be implemented by a server for this game.
 */
public interface ServerInterface {

    /**
     * When called by the game, this method should warn both players about
     * the update. Different kinds of updates are better documented in this
     * game's documentation.
     *
     * @param message JSON message to be communicated.
     * @param playerId ID of the player to which the update should be sent.
     */
    void update(JSONObject message, Integer playerId);

    /**
     * When called by the game, this method should send the prompt to a player
     * and wait until the player answers back with a call id, referring to the
     * behavior call to be executed.
     *
     * @param message Prompt message to be communicated.
     * @param playerId ID of the player to be prompted.
     * @return JSON with the player's response, containing the behavior call's
     *         numerical identifier.
     */
    JSONObject prompt(JSONObject message, Integer playerId);
}
