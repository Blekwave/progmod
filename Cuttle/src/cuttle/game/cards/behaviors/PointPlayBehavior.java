package cuttle.game.cards.behaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlayBehavior extends CardBehavior<BehaviorCall> {

    public PointPlayBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt);
    }

    @Override
    public void call(BehaviorCall c, Prompt p) {
        // ...
    }
}
