package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
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
