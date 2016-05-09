package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Hides a player's hand.
 */
public class HideHand extends PlayerAction {
    public HideHand(Player player){
        super(player, "hide_hand");
    }

    @Override
    public void act() {
        // Do nothing
    }
}
