package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.RaiseVisibility;
import cuttle.game.actions.playeractions.ShowHand;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.EightExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Continuous card behavior for the Eight.
 */
public class EightContinuousBehavior extends ContinuousBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public EightContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "eight_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new RaiseVisibility(game().opponent()));
        if (game().opponent().handIsVisible()){
            game().perform(new ShowHand(game().opponent()));
        }

        card().bindEvent(new EightExitEvent(card()));
    }
}
