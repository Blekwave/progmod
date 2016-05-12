package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Raises a player's protection (registers there's one extra queen on his
 * board).
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
