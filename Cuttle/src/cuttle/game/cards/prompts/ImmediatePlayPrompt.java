package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;

/**
 * Describe this class and the methods exposed by it.
 */
public class ImmediatePlayPrompt extends PlayPrompt {

    private CuttleCard mCard;

    public ImmediatePlayPrompt(CuttleCard card){
        super();
        mCard = card;
    }

    @Override
    public void registerValidCalls(Player player){
        for (Behavior<? extends BehaviorCall, ImmediatePlayPrompt> b : mCard.behaviors()){
            if (b.promptType().equals(type())){
                for (BehaviorCall c : b.listValidCalls()){
                    registerCall(c);
                }
            }
        }
    }
}
