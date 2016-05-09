package cuttle.game.cards.behaviors.pointcardbehaviors;

import cuttle.game.actions.playeractions.PointPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Point play behavior.
 */
public class PointPlayBehavior extends CardBehavior<BehaviorCall, PlayPrompt> {

    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public PointPlayBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "point_play");
    }

    /**
     * Plays the card as a point card.
     *
     * @param c BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void call(BehaviorCall c, PlayPrompt prompt) {
        game().perform(new PointPlay(card()));
    }
}
