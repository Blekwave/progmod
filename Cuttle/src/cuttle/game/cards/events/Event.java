package cuttle.game.cards.events;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;

/**
 * Describe this class and the methods exposed by it.
 */
public abstract class Event {
    public Trigger trigger(){
        return mTrigger;
    }

    public CuttleCard card(){
        return mCard;
    }

    public CuttleGame game(){
        return mGame;
    }

    private Trigger mTrigger;
    private CuttleCard mCard;
    private CuttleGame mGame;

    public Event(Trigger trigger, CuttleCard card){
        mTrigger = trigger;
        mCard = card;
        mGame = card.game();
    }

    public abstract void execute();
}
