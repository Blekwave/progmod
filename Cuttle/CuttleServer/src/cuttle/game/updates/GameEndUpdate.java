package cuttle.game.updates;

import cuttle.game.CuttleGame;
import cuttle.game.Player;
import org.json.JSONObject;

/**
 * Update sent at the end of the game, notifying both player about who won or
 * if there was a draw.
 */
public class GameEndUpdate implements UpdateInterface {

    private Player mWinner;
    private CuttleGame mGame;
    private Boolean mTie;

    /**
     * Constructor used when there is a winner.
     *
     * @param winner Player who won.
     */
    public GameEndUpdate(Player winner){
        mWinner = winner;
        mGame = winner.game();
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
        JSONObject opponent_obj = new JSONObject(obj, JSONObject.getNames(obj));
        if (mTie){
            obj.put("result", "tie");
            opponent_obj.put("result", "tie");
        } else if (mWinner == mGame.player()){
            obj.put("result", "win");
            opponent_obj.put("result", "loss");
        } else {
            obj.put("result", "loss");
            opponent_obj.put("result", "win");
        }

        obj.put("player_points", mGame.player().victoryPoints());
        obj.put("opponent_points", mGame.opponent().victoryPoints());
        opponent_obj.put("player_points", mGame.opponent().victoryPoints());
        opponent_obj.put("opponent_points", mGame.player().victoryPoints());

        return new UpdateContainer(obj, opponent_obj);
    }
}
