package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;
import cuttle.game.cards.CuttleCard;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Reveals a player's hand.
 */
public class ShowHand extends PlayerAction {
    public ShowHand(Player player){
        super(player, "show_hand");
    }

    @Override
    public void act() {
        // Do nothing
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains the action's player's ID as
     * well as his hand's card IDs in order, also shown to his opponent.
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

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the opponent. Contains the action's player's ID as
     * well as his hand's card IDs in order.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
