package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class GameStartUpdate implements UpdateInterface {
    private Player mFirst;

    public GameStartUpdate(Player first){
        mFirst = first;
    }

    @Override
    public UpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "game_start");
        obj.put("starting_player", mFirst.id());
        return new SymmetricUpdateContainer(obj);
    }
}
