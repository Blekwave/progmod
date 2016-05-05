package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class OneOffBehavior<T extends BehaviorCall> extends CardBehavior<T> {
    public OneOffBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    @Override
    public void call(T call, Prompt prompt) {
        ReactionPrompt reactionPrompt = new ReactionPrompt();
        reactionPrompt.prompt(game().opponent());
        if (!reactionPrompt.reacted()){
            procEffect(call, prompt);
        }
        game().perform(new Discard(card()));
    }

    public abstract void procEffect(T call, Prompt prompt);
}
