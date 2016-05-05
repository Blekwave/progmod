package cuttle.game.actions.gameactions;

import cuttle.game.actions.TargetedAction;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.events.Trigger;

/**
 * Describe this class and the methods exposed by it.
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
