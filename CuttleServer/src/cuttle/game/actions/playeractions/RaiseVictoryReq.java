package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import org.json.JSONObject;

/**
 * Raises a player's victory requirements (registers there's one extra king on
 * his board).
 */
public class RaiseVictoryReq extends PlayerAction {

    public RaiseVictoryReq(Player player){
        super(player, "raise_victory_req");
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains the action's player's ID as
     * well as the player's new victory requirements.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildPlayerUpdate(){
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("new_victory_req", player().victoryReq());
        return obj;
    }

    @Override
    public void act() {
        player().raiseVictoryReq();
    }
}
