package cuttle.game.updates;

import org.json.JSONObject;

/**
 * Generalization of the update container for symmetric updates, i.e., updates
 * which send the same information to both players.
 */
public class SymmetricUpdateContainer extends UpdateContainer {
    public SymmetricUpdateContainer(JSONObject update){
        super(update, update);
    }
}
