package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public class ContinuousPlay extends TargetedPlayerAction {
    public ContinuousPlay(CuttleCard target, Player player){
        super(target, player, "continuous_play");
    }

    @Override
    public void act(){
        targetPile().pop(targetPileIndex());
        player().continuousBoard().push(target());
        game().updateCardPile(target(), player().continuousBoard());
    }
}
