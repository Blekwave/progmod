package cuttle.game.actions.gameactions;

import cuttle.game.CuttleGame;
import cuttle.game.actions.Action;

/**
 * Interrupts an ongoing one-off or reaction play. The action itself does
 * nothing, but notifying the players about the interruption is important.
 */
public class Interrupt extends Action {
    public Interrupt(CuttleGame game){
        super(game, "interrupt");
    }

    @Override
    public void act() {
        // Do nothing
    }
}
