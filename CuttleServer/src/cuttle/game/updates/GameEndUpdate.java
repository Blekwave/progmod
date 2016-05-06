package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class GameEndUpdate implements UpdateInterface {

    private Player mWinner;

    public GameEndUpdate(Player winner){
        mWinner = winner;
    }

    @Override
    public SymmetricUpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "game_end");
        obj.put("winner", mWinner.id());
        return new SymmetricUpdateContainer(obj);
    }
}
