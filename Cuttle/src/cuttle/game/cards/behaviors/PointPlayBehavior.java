package cuttle.game.cards.behaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.PointPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlayBehavior extends CardBehavior<BehaviorCall> {

    public PointPlayBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "point_play");
    }

    @Override
    public void call(BehaviorCall c, Prompt p) {
        Player owner = game().cardPile(card()).owner();
        game().perform(new PointPlay(card(), owner));
    }
}
