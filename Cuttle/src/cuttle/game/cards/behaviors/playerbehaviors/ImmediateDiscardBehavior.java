package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.prompts.ImmediatePlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class ImmediateDiscardBehavior extends PlayerBehavior<BehaviorCall, ImmediatePlayPrompt> {
    public ImmediateDiscardBehavior(Player player){
        super(player, PromptType.PlayPrompt, "immediate_discard");
    }

    @Override
    public void call(BehaviorCall call, ImmediatePlayPrompt prompt) {
        game().perform(new Discard(prompt.card()));
    }
}
