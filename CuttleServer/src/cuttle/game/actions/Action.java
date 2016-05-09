package cuttle.game.actions;

import cuttle.game.CuttleGame;
import org.json.JSONObject;

/**
 * Occurrence caused by an Event or a Behavior which modifies the state of the
 * game. It is the occurrence with the smallest granularity and the only one
 * allowed to modify the game and its objects directly.
 */
public abstract class Action {
    public CuttleGame game(){
        return mGame;
    }

    private final CuttleGame mGame;
    private final String mType;

    /**
     * Initializes a new action, associated with a game.
     *
     * @param game Game in which it should be executed.
     * @param type Unique string identifier for this action type.
     */
    public Action(CuttleGame game, String type){
        mGame = game;
        mType = type;
    }

    /**
     * Performs the action, making the necessary changes to the game.
     */
    public abstract void act();

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player.
     *
     * @return New JSONObject with the action's execution information.
     */
    public JSONObject buildPlayerUpdate(){
        JSONObject obj = new JSONObject();
        obj.put("type", "action_update");
        obj.put("action_type", mType);
        return obj;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current opponent.
     *
     * @return New JSONObject with the action's execution information.
     */
    public JSONObject buildOpponentUpdate(){
        return buildPlayerUpdate();
    }

}
