package cuttle.game.actions.gameactions;

import cuttle.game.actions.TargetedAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;

/**
 * Describe this class and the methods exposed by it.
 */
public class Switch extends TargetedAction {
    public Switch(CuttleCard target){
        super(target, "switch");
    }

    @Override
    public void act(){
        targetPile().pop(targetPileIndex());

        Pile oppositePile;
        if (targetPile().equals(game().player().pointBoard())){
            oppositePile = game().opponent().pointBoard();
        } else {
            oppositePile = game().player().pointBoard();
        }

        oppositePile.push(target());
        game().updateCardPile(target(), oppositePile);
    }
}
