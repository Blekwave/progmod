package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class AceOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    public AceOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "ace_one_off");
    }

    private void destroy(CuttleCard card){
        game().perform(new Destroy(card));
    }

    @Override
    public void activateEffect(BehaviorCall call, Prompt prompt) {
        for (CuttleCard c : game().player().pointBoard()){
            destroy(c);
        }
        for (CuttleCard c: game().opponent().pointBoard()){
            destroy(c);
        }
    }

}
