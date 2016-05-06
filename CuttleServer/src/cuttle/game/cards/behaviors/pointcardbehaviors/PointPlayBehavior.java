package cuttle.game.cards.behaviors.pointcardbehaviors;

import cuttle.game.actions.playeractions.PointPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlayBehavior extends CardBehavior<BehaviorCall, PlayPrompt> {

    public PointPlayBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "point_play");
    }

    @Override
    public void call(BehaviorCall c, PlayPrompt prompt) {
        game().perform(new PointPlay(card()));
    }
}
