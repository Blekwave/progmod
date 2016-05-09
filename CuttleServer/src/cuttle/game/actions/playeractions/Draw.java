package cuttle.game.actions.playeractions;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.actions.PlayerAction;
import org.json.JSONObject;

/**
 * A player draws a card.
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

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains the action's player's ID as
     * well as the card drawn.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("drawn", mDrawn.id());
        return obj;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current opponent. Contains the action's player's ID as
     * well as the card drawn, if the player's hand is visible.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildOpponentUpdate(){
        JSONObject obj = super.buildPlayerUpdate();
        if (player().handIsVisible()){
            obj.put("drawn", mDrawn.id());
        }
        return obj;
    }
}
