package cuttle.game.cards.behaviors;

import cuttle.game.actions.playeractions.Discard;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;
import cuttle.game.cards.prompts.ReactionPrompt;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class OneOffBehavior<T extends BehaviorCall, U extends Prompt> extends CardBehavior<T, U> {
    public OneOffBehavior(CuttleCard card, PromptType promptType, String type){
        super(card, promptType, type);
    }

    @Override
    public void call(T call, U prompt) {
        ReactionPrompt reactionPrompt = new ReactionPrompt();
        reactionPrompt.prompt(game().opponent());
        if (!reactionPrompt.reacted()){
            activateEffect(call, prompt);
        }
        game().perform(new Discard(card()));
    }

    public abstract void activateEffect(T call, U prompt);
}
