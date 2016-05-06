package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import cuttle.game.cards.CuttleCard;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class ShuffleHand extends PlayerAction {
    public ShuffleHand(Player player){
        super(player, "shuffle_hand");
    }

    @Override
    public void act() {
        player().hand().shuffle();
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
}
