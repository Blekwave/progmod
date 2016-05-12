package cuttle.game.cards.events;

import cuttle.game.CuttleGame;
import cuttle.game.cards.CuttleCard;

/**
 * Occurrence which can be bound any time during the game to a card and is exe-
 * cuted whenever a trigger is fired.
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

    /**
     * Initializes an Event associated with a Trigger and a card.
     *
     * @param trigger Trigger which triggers this event.
     * @param card Card to which this event is bound.
     */
    public Event(Trigger trigger, CuttleCard card){
        mTrigger = trigger;
        mCard = card;
        mGame = card.game();
    }

    /**
     * Actions to be performed when the trigger occurs.
     */
    public abstract void execute();
}
