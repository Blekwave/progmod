package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import cuttle.game.cards.CuttleCard;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Shuffles a player's hand.
 */
public class ShuffleHand extends PlayerAction {

    public ShuffleHand(Player player){
        super(player, "shuffle_hand");
    }

    @Override
    public void act() {
        player().hand().shuffle();
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains the action's player's ID as
     * well as an array with the IDs of their cards in the new ordering.
     *
     * @return New JSONObject with the action's execution information.
     */
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
