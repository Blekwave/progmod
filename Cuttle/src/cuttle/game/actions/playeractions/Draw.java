package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.actions.PlayerAction;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public class Draw extends PlayerAction {
    public CuttleCard drawn(){
        return mDrawn;
    }

    private CuttleCard mDrawn;

    public Draw(Player player){
        super(player, "draw");
    }

    @Override
    public void act() {
        mDrawn = game().deck().pop();
        player().hand().push(mDrawn);
        game().updateCardPile(mDrawn, player().hand());
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("drawn", mDrawn.id());
        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate(){
        if (player().handIsVisible()){
            return buildPlayerUpdate();
        } else {
            return super.buildOpponentUpdate();
        }
    }
}
