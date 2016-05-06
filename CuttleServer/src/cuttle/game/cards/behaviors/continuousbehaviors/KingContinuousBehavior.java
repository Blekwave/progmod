package cuttle.game.cards.behaviors.continuousbehaviors;

import cuttle.game.actions.playeractions.LowerVictoryReq;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.ContinuousBehavior;
import cuttle.game.cards.events.boardexitevents.KingExitEvent;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class KingContinuousBehavior extends ContinuousBehavior<BehaviorCall> {
    public KingContinuousBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "king_continuous");
    }

    @Override
    public void entryEffect(BehaviorCall call, PlayPrompt prompt) {
        game().perform(new LowerVictoryReq(game().player()));
        card().bindEvent(new KingExitEvent(card()));
    }
}
