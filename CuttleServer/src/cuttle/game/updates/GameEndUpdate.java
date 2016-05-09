package cuttle.game.updates;

import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Update sent at the end of the game, notifying both player about who won or
 * if there was a draw.
 */
public class GameEndUpdate implements UpdateInterface {

    private Player mWinner;

    private Boolean mTie;

    /**
     * Constructor used when there is a winner.
     *
     * @param winner Player who won.
     */
    public GameEndUpdate(Player winner){
        mWinner = winner;
        mTie = false;
    }

    /**
     * Constructor used when there's a draw.
     */
    public GameEndUpdate(){
        mTie = true;
    }

    /**
     * Generates the update container.
     *
     * @return UpdateContainer for the update.
     */
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
