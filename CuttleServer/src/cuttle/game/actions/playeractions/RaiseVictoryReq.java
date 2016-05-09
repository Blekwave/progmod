package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Raises a player's victory requirements (registers there's one extra king on
 * his board).
 */
public class RaiseVictoryReq extends PlayerAction {

    public RaiseVictoryReq(Player player){
        super(player, "raise_victory_req");
    }

    @Override
    public void act() {
        player().raiseVictoryReq();
    }
}
