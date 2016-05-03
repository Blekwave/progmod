package cuttle.game.cards;

import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;
import cuttle.game.CuttleGame;

import java.util.ArrayList;

/**
 * Represents a card in a game of Cuttle.
 */
public abstract class CuttleCard extends PlayingCard {

    private CuttleGame mGame;

    private ArrayList<Behavior> mBehaviors;
    private ArrayList<Event> mEvents;

    private final Integer mId;

    public CuttleCard(CuttleGame game, Integer id, PlayingCard.Suit suit, PlayingCard.Rank rank){
        super(suit, rank);
        mId = id;
        mGame = game;
    }

    public Integer id(){
        return mId;
    }

    public void bindEvent(Event e){
        // ...
    }

    public void trigger(Trigger t){
        // ...
    }
}
