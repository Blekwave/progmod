package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class CardBehavior<T extends BehaviorCall> extends Behavior<T> {
    private CuttleCard mCard;

    public CardBehavior(CuttleCard card, PromptType prompt){
        super(prompt);
        mCard = card;
    }

    public CuttleCard card(){
        return mCard;
    }
}
