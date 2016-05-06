package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.RaiseProtection;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.QueenExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class QueenContinuousBehavior extends ContinuousBehavior<BehaviorCall> {
    public QueenContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "queen_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new RaiseProtection(game().player()));
        card().bindEvent(new QueenExitEvent(card()));
    }
}
