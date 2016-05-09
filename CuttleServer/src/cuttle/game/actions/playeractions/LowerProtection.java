package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Lowers a player's protection (registers there's one less queen on his
 * board).
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
