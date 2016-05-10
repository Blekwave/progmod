package cuttle.game.cards.events.boardexitevents;

import cuttle.game.actions.gameactions.Destroy;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;

/**
 * Event triggered when a Jack's target leaves the board. Destroys the jack
 * which was bound to it.
 */
public class JackTargetExitEvent extends Event {
    private CuttleCard mTarget;

    public JackTargetExitEvent(CuttleCard card, CuttleCard target){
        super(Trigger.OnBoardExit, card);
        mTarget = target;
    }

    @Override
    public void execute() {
        mTarget.unbindEvents();
        game().perform(new Destroy(mTarget));
    }
}
