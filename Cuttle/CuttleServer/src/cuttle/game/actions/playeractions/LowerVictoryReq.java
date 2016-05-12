package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import org.json.JSONObject;

/**
 * Lowers a player's victory requirements (registers there's one less king on
 * his board).
 */
public class LowerVictoryReq extends PlayerAction {

    public LowerVictoryReq(Player player){
        super(player, "lower_victory_req");
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
        player().lowerVictoryReq();
    }
}
