package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.behaviors.TargetedBehaviorCall;
import cuttle.game.cards.prompts.DiscardPrompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class DiscardBehavior extends PlayerBehavior<TargetedBehaviorCall, DiscardPrompt> {
    public DiscardBehavior(Player player){
        super(player, PromptType.DiscardPrompt, "discard");
    }

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
