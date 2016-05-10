package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.gameactions.Switch;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.events.boardexitevents.JackExitEvent;
import cuttle.game.cards.events.boardexitevents.JackTargetExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Continuous card behavior for the Jack.
 */
public class JackContinuousBehavior extends ContinuousBehavior<TargetedBehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public JackContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "jack_continuous");
    }

    /**
     * Lists all valid calls for playing the Jack, i.e., calls associated with
     * every point card on the board.
     *
     * @return List of valid calls.
     */
    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        if (!game().opponent().isProtected()){
            for (CuttleCard c : game().opponent().pointBoard()){
                list.add(new TargetedBehaviorCall(this, c));
            }
        }

        for (CuttleCard c : game().player().pointBoard()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        return list;
    }

    @Override
    public void entryEffect(TargetedBehaviorCall call, PlayPrompt prompt) {
        game().perform(new Switch(call.target()));
        card().bindEvent(new JackExitEvent(card(), call.target()));
        call.target().bindEvent(new JackTargetExitEvent(call.target(), card()));
    }
}
