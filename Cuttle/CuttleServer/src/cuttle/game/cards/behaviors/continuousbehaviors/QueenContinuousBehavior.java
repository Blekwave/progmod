package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.RaiseProtection;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.QueenExitEvent;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

/**
 * Continuous card behavior for the Queen.
 */
public class QueenContinuousBehavior extends ContinuousBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public QueenContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "queen_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new RaiseProtection(game().player()));
        card().bindEvent(new QueenExitEvent(card()));
    }
}
