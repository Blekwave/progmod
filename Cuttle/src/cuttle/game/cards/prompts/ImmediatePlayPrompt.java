package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.behaviors.PlayerBehavior;

/**
 * Describe this class and the methods exposed by it.
 */
public class ImmediatePlayPrompt extends PlayPrompt {

    public CuttleCard card(){
        return mCard;
    }

    private CuttleCard mCard;

    public ImmediatePlayPrompt(CuttleCard card){
        super();
        mCard = card;
    }

    @Override
    public void registerValidCalls(Player player){
        for (CardBehavior<? extends BehaviorCall, ? extends Prompt> b : mCard.behaviors()){
            if (b.promptType().equals(type())){
                for (BehaviorCall c : b.listValidCalls()){
                    registerCall(c);
                }
            }
        }
        if (calls().isEmpty()){
            for (PlayerBehavior<? extends BehaviorCall, ? extends Prompt> b : player.behaviors()){
                if (b.promptType().equals(type())){
                    for (BehaviorCall c : b.listValidCalls()){
                        registerCall(c);
                    }
                }
            }
        }
    }
}
