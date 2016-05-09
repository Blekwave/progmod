package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.prompts.ImmediatePlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Behavior for discarding a card drawn by a Seven one-off play, if there are
 * no valid plays to be made.
 */
public class ImmediateDiscardBehavior extends PlayerBehavior<BehaviorCall, ImmediatePlayPrompt> {
    /**
     * Initializes a new behavior, associated to a player.
     *
     * @param player Player to which this behavior is associated.
     */
    public ImmediateDiscardBehavior(Player player){
        super(player, PromptType.PlayPrompt, "immediate_discard");
    }

    @Override
    public void call(BehaviorCall call, ImmediatePlayPrompt prompt) {
        game().perform(new Discard(prompt.card()));
    }
}
