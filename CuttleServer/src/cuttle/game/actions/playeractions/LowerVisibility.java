package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Lowers a player's hand's visibility (registers there's one less eight on
 * their opponent's board).
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
