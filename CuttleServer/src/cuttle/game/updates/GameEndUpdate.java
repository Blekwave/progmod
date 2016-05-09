package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class GameEndUpdate implements UpdateInterface {

    private Player mWinner;

    private Boolean mTie;

    public GameEndUpdate(Player winner){
        mWinner = winner;
        mTie = false;
    }

    public GameEndUpdate(){
        mTie = true;
    }

    @Override
    public UpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "game_end");
        if (mTie){
            obj.put("result", "tie");
            return new SymmetricUpdateContainer(obj);
        }
        JSONObject loser_obj = new JSONObject(obj, JSONObject.getNames(obj));
        obj.put("result", "win");
        loser_obj.put("result", "loss");

        if (mWinner == mWinner.game().player()){
            return new UpdateContainer(obj, loser_obj);
        } else {
            return new UpdateContainer(loser_obj, obj);
        }
    }
}
