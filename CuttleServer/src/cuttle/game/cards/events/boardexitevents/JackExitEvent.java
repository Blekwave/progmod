package cuttle.game.cards.events.boardexitevents;

import cuttle.game.actions.gameactions.Switch;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;

/**
 * Event triggered when a Jack leaves the board after being played as a con-
 * tinuous card.
 */
public class JackExitEvent extends Event {
    public JackExitEvent(CuttleCard card){
        super(Trigger.OnBoardExit, card);
    }

    @Override
    public void execute() {
        game().perform(new Switch(card()));
    }
}
