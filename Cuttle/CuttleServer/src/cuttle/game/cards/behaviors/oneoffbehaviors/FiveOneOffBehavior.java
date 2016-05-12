package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.playeractions.Draw;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;


/**
 * One-off behavior for the Five.
 */
public class FiveOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public FiveOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "five_one_off");
    }

    /**
     * Card's owner draws two cards.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        for (int i = 0; i < 2; i++){
            game().perform(new Draw(game().player()));
        }
    }
}
