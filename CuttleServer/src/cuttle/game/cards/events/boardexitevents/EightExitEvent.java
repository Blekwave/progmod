package cuttle.game.cards.events.boardexitevents;

import cuttle.game.actions.playeractions.HideHand;
import cuttle.game.actions.playeractions.LowerVisibility;
import cuttle.game.actions.playeractions.ShuffleHand;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;

/**
 * Event triggered when an Eight leaves the board after being played as a con-
 * tinuous card.
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
