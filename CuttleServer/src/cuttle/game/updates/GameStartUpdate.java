package cuttle.game.updates;

import cuttle.game.CuttleGame;
import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class GameStartUpdate implements UpdateInterface {
    private CuttleGame mGame;

    public GameStartUpdate(CuttleGame game){
        mGame = game;
    }

    private JSONObject cardData(CuttleCard card){
        JSONObject obj = new JSONObject();

        obj.put("suit", card.suit().string());
        obj.put("rank", card.rank().string());

        return obj;

    }

    private JSONObject cardById(Pile deck){
        JSONObject obj = new JSONObject();

        for (CuttleCard card : deck){
            obj.put(card.id().toString(), cardData(card));
        }

        return obj;
    }

    private JSONArray cardsInHand(Player player){
        JSONArray obj = new JSONArray();

        for (CuttleCard card : player.hand()){
            obj.put(card.id());
        }

        return obj;
    }

    @Override
    public UpdateContainer buildUpdate() {
        JSONObject obj = new JSONObject();
        obj.put("type", "game_start");
        obj.put("card_by_id", cardById(mGame.deck()));
        JSONObject opponent_obj = new JSONObject(obj, JSONObject.getNames(obj));
        obj.put("player_id", mGame.player().id());
        obj.put("hand", cardsInHand(mGame.player()));
        opponent_obj.put("player_id", mGame.opponent().id());
        opponent_obj.put("hand", cardsInHand(mGame.opponent()));
        return new UpdateContainer(obj, opponent_obj);
    }
}
