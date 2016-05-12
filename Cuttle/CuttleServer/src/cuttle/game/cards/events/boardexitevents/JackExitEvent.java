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

    private CuttleCard mTarget;

    public JackExitEvent(CuttleCard card, CuttleCard target){
        super(Trigger.OnBoardExit, card);
        mTarget = target;
    }

    @Override
    public void execute() {
        game().perform(new Switch(mTarget));
    }
}
