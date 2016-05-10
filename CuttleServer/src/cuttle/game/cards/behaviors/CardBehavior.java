package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;
import org.json.JSONObject;

/**
 * Behavior bound to a specific card.
 */
public abstract class CardBehavior<T extends BehaviorCall, U extends Prompt> extends Behavior<T, U> {

    public CuttleCard card(){
        return mCard;
    }

    private CuttleCard mCard;

    /**
     * Initializes a new Behavior, associated to a card, a prompt type and a
     * string unique identifier of its own.
     *
     * @param card CuttleCard associated to this behavior.
     * @param promptType Prompt type associated to this behavior.
     * @param type Unique string identifier for this behavior type.
     */
    public CardBehavior(CuttleCard card, PromptType promptType, String type){
        super(card.game(), promptType, type);
        mCard = card;
    }

    /**
     * Builds a JSON object regarding a call to this behavior.
     *
     * @param call BehaviorCall associated to this behavior.
     * @return JSONObject containing the behavior type, related card's location
     *                    and index, among other information.
     */
    @Override
    public JSONObject buildCallJSON(T call){
        JSONObject obj = super.buildCallJSON(call);
        obj.put("card", card().genJSON());
        return obj;
    }
}
