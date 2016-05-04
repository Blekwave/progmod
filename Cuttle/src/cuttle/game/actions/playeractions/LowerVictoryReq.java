package cuttle.game.actions.playeractions;

    import cuttle.game.Player;
    import cuttle.game.actions.PlayerAction;

/**
 * Describe this class and the methods exposed by it.
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
