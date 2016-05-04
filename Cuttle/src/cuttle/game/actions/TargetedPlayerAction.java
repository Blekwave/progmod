package cuttle.game.actions;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class TargetedPlayerAction extends PlayerAction {

    public CuttleCard target(){
        return mTarget;
    }

    private CuttleCard mTarget;

    public TargetedPlayerAction(CuttleCard target, Player player, String type){
        super(player, type);
        mTarget = target;
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        Pile pile = game().cardPile(mTarget);

        obj.put("target_pile", pile.name());
        obj.put("target_pile_index", pile.indexOf(mTarget));
        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
