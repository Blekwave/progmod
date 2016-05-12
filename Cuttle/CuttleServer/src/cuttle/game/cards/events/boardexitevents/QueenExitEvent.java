package cuttle.game.cards.events.boardexitevents;

import cuttle.game.actions.playeractions.LowerProtection;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;

/**
 * Event triggered when a Queen leaves the board after being played as a con-
 * tinuous card.
 */
public class QueenExitEvent extends Event {
    public QueenExitEvent(CuttleCard card){
        super(Trigger.OnBoardExit, card);
    }

    @Override
    public void execute() {
        game().perform(new LowerProtection(card().owner()));
    }
}
