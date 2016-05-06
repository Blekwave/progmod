package cuttle.game.updates;

import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class SymmetricUpdateContainer extends UpdateContainer {
    public SymmetricUpdateContainer(JSONObject update){
        super(update, update);
    }
}
