package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.TargetedPlayerAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;

/**
 * Describe this class and the methods exposed by it.
 */
public class PointPlay extends TargetedPlayerAction {
    public PointPlay(CuttleCard target, Player player){
        super(target, player, "point_play");
    }

    @Override
    public void act(){
        targetPile().pop(targetPileIndex());
        player().pointBoard().push(target());
        game().updateCardPile(target(), player().pointBoard());
    }
}
