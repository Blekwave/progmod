package cuttle.game.actions;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Action which concerns a player.
 */
public abstract class PlayerAction extends Action {

    public Player player(){
        return mPlayer;
    }

    private Player mPlayer;

    /**
     * Initializes a PlayerAction.
     *
     * @param player Player to whom the action is related.
     * @param type Unique string identifier for this action type.
     */
    public PlayerAction(Player player, String type){
        super(player.game(), type);
        mPlayer = player;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains the action's player's ID as
     * well as any other information.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildPlayerUpdate(){
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("player", mPlayer.id());
        return obj;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current opponent. Contains the action's player's ID as
     * well as any other information.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildOpponentUpdate(){
        return buildPlayerUpdate();
    }
}
