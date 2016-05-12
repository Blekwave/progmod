package cuttle.game.cards.events.boardexitevents;

import cuttle.game.actions.playeractions.RaiseVictoryReq;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;

/**
 * Event triggered when a King leaves the board after being played as a con-
 * tinuous card.
 */
public class KingExitEvent extends Event {
    public KingExitEvent(CuttleCard card){
        super(Trigger.OnBoardExit, card);
    }

    @Override
    public void execute() {
        game().perform(new RaiseVictoryReq(card().owner()));
    }
}
