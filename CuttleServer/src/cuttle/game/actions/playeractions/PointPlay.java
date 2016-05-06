package cuttle.game.actions.playeractions;

import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlay extends TargetedPlayerAction {
    public PointPlay(CuttleCard target){
        super(target, target.owner(), "point_play");
    }

    @Override
    public void act(){
        targetPile().pop(targetPileIndex());
        player().pointBoard().push(target());
        game().updateCardPile(target(), player().pointBoard());
    }
}
