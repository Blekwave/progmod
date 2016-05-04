package cuttle.game.cards.behaviors;

import cuttle.game.cards.prompts.Prompt;
import cuttle.game.cards.prompts.PromptType;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Behavior<T extends BehaviorCall> {
    private PromptType mPromptType;

    public Behavior(PromptType promptType){
        mPromptType = promptType;
    }


    public abstract ArrayList<T> listValidCalls();

    public abstract void call(T c, Prompt p);
}
