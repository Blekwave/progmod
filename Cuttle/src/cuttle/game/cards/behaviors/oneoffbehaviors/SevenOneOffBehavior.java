package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.playeractions.Draw;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.cards.prompts.ImmediatePlayPrompt;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public class SevenOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    public SevenOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "seven_one_off");
    }

    @Override
    public void activateEffect(BehaviorCall call, Prompt prompt) {
        Draw drawAction = new Draw(game().player());
        game().perform(drawAction);
        ImmediatePlayPrompt immediatePlayPrompt = new ImmediatePlayPrompt(drawAction.drawn());
        immediatePlayPrompt.prompt(game().player());
    }
}
