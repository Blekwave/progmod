package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
 */
public class LowerVisibility extends PlayerAction {

    public LowerVisibility(Player player){
        super(player, "lower_visibility");
    }

    @Override
    public void act() {
        player().lowerVisibility();
    }
}
