package cuttle.game.cards.behaviors.playerbehaviors;

import cuttle.game.Player;
import cuttle.game.actions.playeractions.Draw;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class DrawBehavior extends PlayerBehavior<BehaviorCall, PlayPrompt> {
    public DrawBehavior(Player player){
        super(player, PromptType.PlayPrompt, "draw");
    }

    @Override
    public void call(BehaviorCall call, PlayPrompt prompt) {
        if (!player().game().deck().size().empty()){
            game().perform(new Draw(player()));
        } else {
            prompt.pass();
        }
    }
}
