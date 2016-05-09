package cuttle.game.actions.playeractions;

import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * A player discards a given card.
 */
public class Discard extends TargetedPlayerAction {

    public Discard(CuttleCard target){
        super(target, target.owner(), "discard");
    }

    @Override
    public void act() {
        targetPile().pop(targetPileIndex());
        game().scrapPile().push(target());
        game().updateCardPile(target(), game().scrapPile());
    }
}
