package cuttle.game.actions;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Action which targets a card.
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


    /**
     * Initializes a new action, associated with a target card.
     *
     * @param target Card to be targeted by the action.
     * @param type Unique string identifier for this action type.
     */
    public TargetedAction(CuttleCard target, String type){
        super(target.game(), type);
        mTarget = target;
        mTargetPile = game().cardPile(target);
        mTargetPileIndex = mTargetPile.indexOf(target);
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains information about the target
     * card's pile and index in that pile.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("target", target().genJSON());
        return obj;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current opponent. Contains information about the target
     * card's pile and index in that pile.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
