package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
 */
public class RaiseProtection extends PlayerAction {

    public RaiseProtection(Player player){
        super(player, "raise_protection");
    }

    @Override
    public void act() {
        player().raiseProtection();
    }
}
