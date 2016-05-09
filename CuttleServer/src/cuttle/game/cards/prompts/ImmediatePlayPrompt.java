package cuttle.game.cards.prompts;

import cuttle.game.Player;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.BehaviorCall;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.behaviors.PlayerBehavior;

/**
 * Prompt triggered when a player draws a card and must play it immediately, as
 * result of a Seven's one-off play. Specific type of PlayPrompt.
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

    /**
     * Lists all behaviors associated with the target card. If no calls can be
     * performed, allows the player to discard the card which was drawn.
     *
     * @param player Player whose Behaviors should be iterated.
     */
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
            for (BehaviorCall c : player.immediateDiscardBehavior().listValidCalls()){
                registerCall(c);
            }
        }
    }
}
