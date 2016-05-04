package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
 */
public class LowerProtection extends PlayerAction {

    public LowerProtection(Player player){
        super(player, "lower_protection");
    }

    @Override
    public void act() {
        player().lowerProtection();
    }
}
