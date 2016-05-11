package cuttle.game.cards.behaviors.oneoffbehaviors;

import cuttle.game.actions.playeractions.Draw;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.OneOffBehavior;
import cuttle.game.prompts.ImmediatePlayPrompt;
import cuttle.game.prompts.PlayPrompt;
import cuttle.game.prompts.PromptType;

/**
 * One-off behavior for the Seven.
 */
public class SevenOneOffBehavior extends OneOffBehavior<BehaviorCall> {
    /**
     * Initializes a new behavior, associated to a card.
     *
     * @param card Card to which this behavior is associated.
     */
    public SevenOneOffBehavior(CuttleCard card){
        super(card, PromptType.PlayPrompt, "seven_one_off");
    }

    /**
     * Draws a card and forces the player to immediately play it or discard it
     * if that's impossible.
     *
     * @param call BehaviorCall associated to this behavior.
     * @param prompt Prompt which prompted this behavior.
     */
    @Override
    public void activateEffect(BehaviorCall call, PlayPrompt prompt) {
        Draw drawAction = new Draw(game().player());
        game().perform(drawAction);
        ImmediatePlayPrompt immediatePlayPrompt = new ImmediatePlayPrompt(drawAction.drawn());
        immediatePlayPrompt.prompt(game().player());
    }
}
