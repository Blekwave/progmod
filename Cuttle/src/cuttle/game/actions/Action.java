package cuttle.game.actions;

import cuttle.game.CuttleGame;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Action {

    public CuttleGame game(){
        return mGame;
    }

    private final CuttleGame mGame;
    private final String mType;

    public Action(CuttleGame game, String type){
        mGame = game;
        mType = type;
    }

    public abstract void act();

    public JSONObject buildPlayerUpdate(){
        JSONObject obj = new JSONObject();
        obj.put("type", mType);
        return obj;
    }

    public JSONObject buildOpponentUpdate(){
        return buildPlayerUpdate();
    }
}
