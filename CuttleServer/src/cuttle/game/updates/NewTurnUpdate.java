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
        return new SymmetricUpdateContainer(obj);
    }
}
