package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class TwoOneOffBehavior extends OneOffBehavior<TargetedBehaviorCall> {
    public TwoOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "two_one_off");
    }

    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        if (!game().opponent().isProtected()){
            for (CuttleCard c : game().opponent().continuousBoard()){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        return list;
    }

    @Override
    public void activateEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Destroy(call.target()));
    }
}
