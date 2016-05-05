package cuttle.game.actions;

import cuttle.game.CuttleGame;
import cuttle.game.updates.UpdateContainer;
import cuttle.game.updates.UpdateInterface;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Action implements UpdateInterface {

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
        obj.put("type", "action_update");
        obj.put("action_type", mType);
        return obj;
    }

    public JSONObject buildOpponentUpdate(){
        return buildPlayerUpdate();
    }

    @Override
    public UpdateContainer buildUpdate(){
        return new UpdateContainer(buildPlayerUpdate(), buildOpponentUpdate());
    }
}
