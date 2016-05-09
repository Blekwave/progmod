package cuttle.game.actions;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import org.json.JSONObject;

/**
 * Action which concerns a player and targets a card.
 */
public abstract class TargetedPlayerAction extends PlayerAction {

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
     * Initializes a new action, associated with a target card and a player.
     *
     * @param target Card to be targeted by the action.
     * @param player Player to whom the action is related.
     * @param type Unique string identifier for this action type.
     */
    public TargetedPlayerAction(CuttleCard target, Player player, String type){
        super(player, type);
        mTarget = target;
        mTargetPile = game().cardPile(target);
        mTargetPileIndex = mTargetPile.indexOf(target);
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the current player. Contains information about the target
     * card's pile and index in that pile, the action's player's ID and any
     * other information.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildPlayerUpdate() {
        JSONObject obj = super.buildPlayerUpdate();
        obj.put("target_pile", mTargetPile.name());
        obj.put("target_pile_index", mTargetPileIndex);
        return obj;
    }

    /**
     * Builds a JSON update object regarding to the action performed, to be
     * relayed to the opponent. Contains information about the target
     * card's pile and index in that pile, the action's player's ID and any
     * other information.
     *
     * @return New JSONObject with the action's execution information.
     */
    @Override
    public JSONObject buildOpponentUpdate() {
        return buildPlayerUpdate();
    }
}
