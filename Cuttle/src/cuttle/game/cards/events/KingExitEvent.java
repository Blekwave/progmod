package cuttle.game.cards.events;

import cuttle.game.actions.playeractions.RaiseVictoryReq;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
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
