package cuttle.game.cards.prompts;

import cuttle.game.BehaviorIterator;
import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class PlayPrompt extends Prompt {
    public PlayPrompt(){
        super(PromptType.PlayPrompt);
    }

    @Override
    public void prompt(Player player) {
        BehaviorIterator it = player.behaviorIterator();
        ArrayList<BehaviorCall> calls = new ArrayList<>();

        while (it.hasNext()){
            Behavior<BehaviorCall> b = it.next();
            for (BehaviorCall c : b.listValidCalls()){
                calls.add(c);
                // Register call id and details in the JSON object
            }
        }



    }
}
