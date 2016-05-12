package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Raises a player's hand's visibility (registers there's one extra eight on
 * their opponent's board).
 */
public class RaiseVisibility extends PlayerAction {

    public RaiseVisibility(Player player){
        super(player, "raise_visibility");
    }

    @Override
    public void act() {
        player().raiseVisibility();
    }
}
