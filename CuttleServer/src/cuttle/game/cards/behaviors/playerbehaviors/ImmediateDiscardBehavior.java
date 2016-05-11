package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.prompts.ImmediatePlayPrompt;
import cuttle.game.prompts.PromptType;

import java.util.ArrayList;

/**
 * Behavior for discarding a card drawn by a Seven one-off play, if there are
 * no valid plays to be made.
 */
public class ImmediateDiscardBehavior extends PlayerBehavior<TargetedBehaviorCall, ImmediatePlayPrompt> {
    /**
     * Initializes a new behavior, associated to a player.
     *
     * @param player Player to which this behavior is associated.
     */
    public ImmediateDiscardBehavior(Player player){
        super(player, PromptType.PlayPrompt, "immediate_discard");
    }

    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        CuttleCard last = player().hand().get(player().hand().size() - 1);

        list.add(new TargetedBehaviorCall(this, last));

        return list;
    }

    @Override
    public void call(TargetedBehaviorCall call, ImmediatePlayPrompt prompt) {
        game().perform(new Discard(call.target()));
    }
}
