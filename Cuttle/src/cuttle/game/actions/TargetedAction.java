package cuttle.game.actions;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class TargetedAction extends Action {

    public CuttleCard target(){
        return mTarget;
    }

    private CuttleCard mTarget;

    public TargetedAction(CuttleCard target, String type){
        super(target.game(), type);
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
