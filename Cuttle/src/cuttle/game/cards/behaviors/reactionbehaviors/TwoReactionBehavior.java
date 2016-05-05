package cuttle.game.cards.behaviors.reactionbehaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Describe this class and the methods exposed by it.
 */
public class TwoReactionBehavior extends CardBehavior<BehaviorCall, ReactionPrompt> {
    public TwoReactionBehavior(CuttleCard card){
        super(card, PromptType.ReactionPrompt, "two_reaction");
    }

    @Override
    public void call(BehaviorCall call, ReactionPrompt prompt) {
        prompt.react();
    }
}
