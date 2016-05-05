package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import cuttle.game.cards.CuttleCard;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class ShowHand extends PlayerAction {
    public ShowHand(Player player){
        super(player, "show_hand");
    }

    @Override
    public void act() {
        // Do nothing
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();

        JSONArray handIdList = new JSONArray();
        for (CuttleCard c : player().hand()){
            handIdList.put(c.id());
        }
        obj.put("player_hand", handIdList);

        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
