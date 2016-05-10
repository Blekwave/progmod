package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Update sent every turn announcing the event and whose turn it is.
 */
public class NewTurnUpdate implements UpdateInterface {

    private Player mPlayer;

    /**
     * Initializes the update.
     *
     * @param player Player whose turn it is.
     */
    public NewTurnUpdate(Player player){
        mPlayer = player;
    }

    /**
     * Generates the update container.
     *
     * @return UpdateContainer for the update.
     */
    @Override
    public UpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "new_turn");
        obj.put("player", mPlayer.id());
        JSONObject opponent_obj = new JSONObject(obj, JSONObject.getNames(obj));
        obj.put("player_points", mPlayer.victoryPoints());
        obj.put("opponent_points", mPlayer.opponent().victoryPoints());
        opponent_obj.put("player_points", mPlayer.opponent().victoryPoints());
        opponent_obj.put("opponent_points", mPlayer.victoryPoints());
        return new UpdateContainer(obj, opponent_obj);
    }
}
