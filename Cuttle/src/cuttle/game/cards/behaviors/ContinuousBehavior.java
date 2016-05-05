package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.ContinuousPlay;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class ContinuousBehavior<T extends BehaviorCall> extends CardBehavior<T, PlayPrompt> {
    public ContinuousBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    @Override
    public void call(T call, PlayPrompt prompt) {
        game().perform(new ContinuousPlay(card()));
        entryEffect(call, prompt);
    }

    public abstract void entryEffect(T call, PlayPrompt prompt);
}
