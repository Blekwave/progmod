package cuttle.game.updates;

import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class UpdateContainer {
    public JSONObject playerUpdate() {
        return mPlayerUpdate;
    }

    public JSONObject opponentUpdate() {
        return mOpponentUpdate;
    }

    private JSONObject mPlayerUpdate;
    private JSONObject mOpponentUpdate;

    public UpdateContainer(JSONObject playerUpdate, JSONObject opponentUpdate){
        mPlayerUpdate = playerUpdate;
        mOpponentUpdate = opponentUpdate;
    }

}
