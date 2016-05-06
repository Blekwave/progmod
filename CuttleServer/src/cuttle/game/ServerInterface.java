package cuttle.game;

import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public interface ServerInterface {
    void update(JSONObject message, Integer playerId);
    JSONObject prompt(JSONObject message, Integer playerId);
}
