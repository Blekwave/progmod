package cuttle.game.cards;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;
import cuttle.game.CuttleGame;

import java.util.ArrayList;

/**
 * Represents a card in a game of Cuttle.
 *
 * Has a list of its own behaviors, to be bound to it by subclasses' construc-
 * tors and a list of events, to be bound to it by behaviors and other in-game
 * occurrences.
 */
public abstract class CuttleCard extends PlayingCard {

    public Integer id(){
        return mId;
    }

    public CuttleGame game(){
        return mGame;
    }

    public ArrayList<CardBehavior> behaviors(){
        return mBehaviors;
    }

    private CuttleGame mGame;

    private ArrayList<CardBehavior> mBehaviors;
    private ArrayList<Event> mEvents;

    private final Integer mId;

    /**
     * Initializes a new CuttleCard.
     *
     * @param game Game to which it belongs.
     * @param id Its numeric ID.
     * @param suit Its suit.
     * @param rank Its rank.
     */
    public CuttleCard(CuttleGame game, Integer id, PlayingCard.Suit suit, PlayingCard.Rank rank){
        super(suit, rank);
        mId = id;
        mGame = game;
        mBehaviors = new ArrayList<>();
        mEvents = new ArrayList<>();
    }

    /**
     * Gets the card's owner.
     *
     * @return Player which owns this card or null if it belongs to the deck of
     *         cards or the scrap pile.
     */
    public Player owner(){
        return game().cardPile(this).owner();
    }

    /**
     * Binds a behavior to this card.
     *
     * @param cardBehavior Behavior to be bound to the card.
     */
    public void bindBehavior(CardBehavior cardBehavior){
        mBehaviors.add(cardBehavior);
    }

    /**
     * Binds an event to this card.
     *
     * @param event Event to be bound to the card.
     */
    public void bindEvent(Event event){
        mEvents.add(event);
    }

    /**
     * Triggers all events with a given Trigger bound to this card.
     *
     * @param trigger Trigger whose events should be triggered.
     */
    public void trigger(Trigger trigger){
        for (Event event : mEvents){
            if (event.trigger().equals(trigger)){
                event.execute();
            }
        }
    }
}
