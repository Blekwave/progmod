package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class SixOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    public SixOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "six_one_off");
    }

    private void destroy(CuttleCard card){
        game().perform(new Destroy(card));
    }

    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        for (CuttleCard c : game().player().continuousBoard()){
            destroy(c);
        }
        for (CuttleCard c: game().opponent().continuousBoard()){
            destroy(c);
        }
    }

}
