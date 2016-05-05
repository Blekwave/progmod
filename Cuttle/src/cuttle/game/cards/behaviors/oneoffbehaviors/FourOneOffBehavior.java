package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.prompts.DiscardPrompt;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class FourOneOffBehavior extends OneOffBehavior<BehaviorCall, PlayPrompt> {
    public FourOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "four_one_off");
    }

    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        for (int i = 0; i < 2; i++){
            DiscardPrompt discardPrompt = new DiscardPrompt();
            discardPrompt.prompt(game().opponent());
        }
    }
}
