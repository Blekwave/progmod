package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.gameactions.Switch;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.events.boardexitevents.JackExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class JackContinuousBehavior extends ContinuousBehavior<TargetedBehaviorCall, PlayPrompt> {
    public JackContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "jack_continuous");
    }

    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : game().player().pointBoard()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        for (CuttleCard c : game().opponent().pointBoard()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        return list;
    }

    @Override
    public void entryEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Switch(call.target()));
        card().bindEvent(new JackExitEvent(card()));
    }
}
