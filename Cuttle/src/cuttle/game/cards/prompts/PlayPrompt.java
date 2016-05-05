package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe this class and the methods exposed by it.
 */
public class PlayPrompt extends Prompt {
    public PlayPrompt(){
        super(PromptType.PlayPrompt);
    }

    @Override
    public void registerValidCalls(Player player){
        Iterator<Behavior> it = player.behaviorIterator();

        while (it.hasNext()){
            Behavior<? extends BehaviorCall> b = it.next();
            if (b.promptType().equals(type())){
                for (BehaviorCall c : b.listValidCalls()){
                    registerCall(c);
                }
            }
        }
    }
}
