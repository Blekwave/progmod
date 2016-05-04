package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.actions.PlayerAction;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class Draw extends PlayerAction {
    public Draw(Player player){
        super(player, "draw");
    }

    @Override
    public void act() {
        CuttleCard drawn = game().deck().pop();
        player().hand().push(drawn);
        game().updateCardPile(drawn, player().hand());
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("drawn", player().hand().last());
        return obj;
    }
}
