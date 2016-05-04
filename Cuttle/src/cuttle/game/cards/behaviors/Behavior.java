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

    public ArrayList<? extends BehaviorCall> listValidCalls() {
        ArrayList<BehaviorCall> list = new ArrayList<>();
        list.add(new BehaviorCall(this));
        return list;
    }

    public abstract void call(T c, Prompt p);
}
