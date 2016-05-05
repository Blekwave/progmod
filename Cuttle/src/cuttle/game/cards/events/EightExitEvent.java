package cuttle.game.cards.events;

import cuttle.game.actions.playeractions.HideHand;
import cuttle.game.actions.playeractions.LowerVisibility;
import cuttle.game.actions.playeractions.ShuffleHand;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public class EightExitEvent extends Event {
    public EightExitEvent(CuttleCard card){
        super(Trigger.OnBoardExit, card);
    }

    @Override
    public void execute() {
        game().perform(new LowerVisibility(card().owner()));
        if (!card().owner().handIsVisible()){
            game().perform(new HideHand(card().owner()));
            game().perform(new ShuffleHand(card().owner()));
        }
    }
}
