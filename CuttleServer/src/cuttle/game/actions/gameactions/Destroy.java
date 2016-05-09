package cuttle.game.actions.gameactions;

import cuttle.game.actions.TargetedAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Trigger;

/**
 * Destroys a card from a player's board.
 *
 * Triggers OnBoardExit events associated with the card, if there are any.
 */
public class Destroy extends TargetedAction {
    public Destroy(CuttleCard target){
        super(target, "destroy");
    }

    @Override
    public void act(){
        target().trigger(Trigger.OnBoardExit);
        targetPile().pop(targetPileIndex());
        game().scrapPile().push(target());
        game().updateCardPile(target(), game().scrapPile());
    }
}
