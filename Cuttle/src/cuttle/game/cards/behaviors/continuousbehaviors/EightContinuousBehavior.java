package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.RaiseVisibility;
import cuttle.game.actions.playeractions.ShowHand;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.EightExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class EightContinuousBehavior extends ContinuousBehavior<BehaviorCall, PlayPrompt> {
    public EightContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "eight_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new RaiseVisibility(game().player()));
        if (game().player().handIsVisible()){
            game().perform(new ShowHand(game().player()));
        }

        card().bindEvent(new EightExitEvent(card()));
    }
}
