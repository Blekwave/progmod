package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.actions.PlayerAction;

/**
 * Lowers a player's victory requirements (registers there's one less king on
 * his board).
 */
public class LowerVictoryReq extends PlayerAction {

    public LowerVictoryReq(Player player){
        super(player, "lower_victory_req");
    }

    @Override
    public void act() {
        player().lowerVictoryReq();
    }
}
