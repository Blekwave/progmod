package cuttle.game.cards.events;

import cuttle.game.actions.gameactions.Switch;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
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
