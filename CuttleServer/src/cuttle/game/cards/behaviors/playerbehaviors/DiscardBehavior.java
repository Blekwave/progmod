package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.prompts.DiscardPrompt;
import cuttle.game.prompts.PromptType;

import java.util.ArrayList;

/**
 * Discard behavior, which happens when a Four is played.
 */
public class DiscardBehavior extends PlayerBehavior<TargetedBehaviorCall, DiscardPrompt> {
    /**
     * Initializes a new behavior, associated to a player.
     *
     * @param player Player to which this behavior is associated.
     */
    public DiscardBehavior(Player player){
        super(player, PromptType.DiscardPrompt, "discard");
    }

    /**
     * Lists valid discard calls, i.e., a call for every card in the player's
     * hand.
     *
     * @return List of valid calls.
     */
    @Override
    public ArrayList<TargetedBehaviorCall> listValidCalls() {
        ArrayList<TargetedBehaviorCall> list = new ArrayList<>();

        for (CuttleCard c : player().hand()){
            list.add(new TargetedBehaviorCall(this, c));
        }

        return list;
    }

    @Override
    public void call(TargetedBehaviorCall call, DiscardPrompt prompt){
        game().perform(new Discard(call.target()));
    }
}
