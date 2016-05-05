package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Describe this class and the methods exposed by it.
 */
public class PassBehavior extends PlayerBehavior<BehaviorCall, ReactionPrompt> {
    public PassBehavior(Player player){
        super(player, PromptType.ReactionPrompt, "pass");
    }

    @Override
    public void call(BehaviorCall call, ReactionPrompt prompt) {
        // Do nothing
    }
}
