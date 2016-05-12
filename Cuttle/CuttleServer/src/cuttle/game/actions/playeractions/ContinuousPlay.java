package cuttle.game.actions.playeractions;

import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * A player plays a card as a continuous card.
 */
public class ContinuousPlay extends TargetedPlayerAction {
    public ContinuousPlay(CuttleCard target){
        super(target, target.owner(), "continuous_play");
    }

    @Override
    public void act(){
        targetPile().pop(targetPileIndex());
        player().continuousBoard().push(target());
        game().updateCardPile(target(), player().continuousBoard());
    }
}
