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

    public Pile targetPile(){
        return mTargetPile;
    }

    public Integer targetPileIndex(){
        return mTargetPileIndex;
    }

    private CuttleCard mTarget;
    private Pile mTargetPile;
    private Integer mTargetPileIndex;

    public TargetedAction(CuttleCard target, String type){
        super(target.game(), type);
        mTarget = target;
        mTargetPile = game().cardPile(target);
        mTargetPileIndex = mTargetPile.indexOf(target);
    }

    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("target_pile", mTargetPile.name());
        obj.put("target_pile_index", mTargetPileIndex);
        return obj;
    }

    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
