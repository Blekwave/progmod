package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Draw;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

/**
 * Behavior for drawing a card.
 */
public class DrawBehavior extends PlayerBehavior<BehaviorCall, PlayPrompt> {
    /**
     * Initializes a new behavior, associated to a player.
     *
     * @param player Player to which this behavior is associated.
     */
    public DrawBehavior(Player player){
        super(player, PromptType.PlayPrompt, "draw");
    }

    /**
     * Draws a card or passes the turn if the deck is empty.
     *
     * @param call BehaviorCall to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void call(BehaviorCall call, PlayPrompt prompt) {
        if (!player().game().deck().isEmpty()){
            game().perform(new Draw(player()));
        } else {
            prompt.pass();
        }
    }
}
