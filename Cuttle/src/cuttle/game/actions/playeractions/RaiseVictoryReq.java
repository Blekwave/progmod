package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
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
