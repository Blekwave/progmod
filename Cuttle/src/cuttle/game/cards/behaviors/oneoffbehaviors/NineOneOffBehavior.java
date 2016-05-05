package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.gameactions.Return;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class NineOneOffBehavior extends OneOffBehavior<TargetedBehaviorCall> {
    public NineOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "nine_one_off");
    }

    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : game().opponent().continuousBoard()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        return list;
    }

    @Override
    public void activateEffect(TargetedBehaviorCall call, Prompt prompt) {
        game().perform(new Return(call.target()));
    }
}
