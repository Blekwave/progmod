package cuttle.game.actions.gameactions;

import cuttle.game.actions.TargetedAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Trigger;

/**
 * Returns a card from the board to the top of the deck.
 *
 * Triggers OnBoardExit events associated with the card, if there are any.
 */
public class Return extends TargetedAction {
    public Return(CuttleCard target){
        super(target, "return");
    }

    @Override
    public void act(){
        target().trigger(Trigger.OnBoardExit);
        targetPile().pop(targetPileIndex());
        game().deck().push(target());
        game().updateCardPile(target(), game().scrapPile());
    }
}
