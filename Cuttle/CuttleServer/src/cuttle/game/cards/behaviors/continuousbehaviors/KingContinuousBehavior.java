package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.LowerVictoryReq;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.KingExitEvent;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

/**
 * Continuous card behavior for the King.
 */
public class KingContinuousBehavior extends ContinuousBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public KingContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "king_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new LowerVictoryReq(game().player()));
        card().bindEvent(new KingExitEvent(card()));
    }
}
