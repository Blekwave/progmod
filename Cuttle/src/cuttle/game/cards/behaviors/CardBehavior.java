package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.prompts.PromptType;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class CardBehavior<T extends BehaviorCall> extends Behavior<T> {

    public CuttleCard card(){
        return mCard;
    }

    private CuttleCard mCard;

    public CardBehavior(CuttleCard card, PromptType prompt, String type){
        super(card.game(), prompt, type);
        mCard = card;
    }

    @Override
    public JSONObject buildCallJSON(T call){
        JSONObject obj = super.buildCallJSON(call);
        Pile cardPile = card().game().cardPile(card());
        obj.put("card_location", cardPile.name());
        obj.put("card_index", cardPile.indexOf(card()));
        return obj;
    }
}
