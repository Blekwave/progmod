package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class NewTurnUpdate implements UpdateInterface {

    private Player mPlayer;

    public NewTurnUpdate(Player player){
        mPlayer = player;
    }

    @Override
    public UpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "new_turn");
        obj.put("player", mPlayer.id());
        return new SymmetricUpdateContainer(obj);
    }
}
