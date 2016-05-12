package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.prompts.DiscardPrompt;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

/**
 * One-off behavior for the Four.
 */
public class FourOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public FourOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "four_one_off");
    }

    /**
     * Forces the opponent to discard two cards.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        for (int i = 0; i < 2; i++){
            if (!game().opponent().hand().isEmpty()){
                DiscardPrompt discardPrompt = new DiscardPrompt();
                discardPrompt.prompt(game().opponent());
            }
        }
    }
}
