package cuttle.game.actions;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class PlayerAction extends Action {

    public Player player(){
        return mPlayer;
    }

    private Player mPlayer;

    public PlayerAction(Player player, String type){
        super(type);
        mPlayer = player;
    }

    @Override
    public JSONObject buildPlayerUpdate(){
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("player", mPlayer.id());
        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate(){
        return buildPlayerUpdate();
    }
}
