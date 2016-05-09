package cuttle.game.updates;

import cuttle.game.CuttleGame;
import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Update sent when the game starts, containing three essential pieces of data:
 * a player's ID (which they don't know yet), their initial cards (five or six,
 * depending on whether they start) and a relation between each card's ID and
 * its suit and rank.
 */
public class GameStartUpdate implements UpdateInterface {
    private CuttleGame mGame;

    /**
     * Initializes the update.
     *
     * @param game Game which has started.
     */
    public GameStartUpdate(CuttleGame game){
        mGame = game;
    }

    /**
     * Generates a JSONObject containing a card's rank and suit.
     *
     * @param card Card whose rank and suit should be provided.
     * @return JSONObject with the card's rank and suit.
     */
    private JSONObject cardData(CuttleCard card){
        JSONObject obj = new JSONObject();

        obj.put("suit", card.suit().string());
        obj.put("rank", card.rank().string());

        return obj;

    }

    /**
     * Generates a JSONObject relating each card in a deck of card's numeric ID
     * to its cardData.
     *
     * @param deck Deck of cards whose cards should be queried.
     * @return JSONObject defined above.
     */
    private JSONObject cardById(Pile deck){
        JSONObject obj = new JSONObject();

        for (CuttleCard card : deck){
            obj.put(card.id().toString(), cardData(card));
        }

        return obj;
    }

    /**
     * Generates a JSONArray containing a list of a player's hand's cards.
     *
     * @param player Player whose hand should be analysed.
     * @return JSONArray with the hand's card's numeric IDs.
     */
    private JSONArray cardsInHand(Player player){
        JSONArray obj = new JSONArray();

        for (CuttleCard card : player.hand()){
            obj.put(card.id());
        }

        return obj;
    }

    /**
     * Generates the update container.
     *
     * @return UpdateContainer for the update.
     */
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
