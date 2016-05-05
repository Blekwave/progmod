package cuttle.game.cards;

import cuttle.game.Player;
import cuttle.game.cards.behaviors.Behavior;
import cuttle.game.cards.behaviors.CardBehavior;
import cuttle.game.cards.events.Event;
import cuttle.game.cards.events.Trigger;
import cuttle.game.CuttleGame;

import java.util.ArrayList;

/**
 * Represents a card in a game of Cuttle.
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

    public CuttleCard(CuttleGame game, Integer id, PlayingCard.Suit suit, PlayingCard.Rank rank){
        super(suit, rank);
        mId = id;
        mGame = game;
        mBehaviors = new ArrayList<>();
        mEvents = new ArrayList<>();
    }

    public Player owner(){
        return game().cardPile(this).owner();
    }

    public void bindBehavior(CardBehavior cardBehavior){
        mBehaviors.add(cardBehavior);
    }

    public void bindEvent(Event event){
        mEvents.add(event);
    }

    public void trigger(Trigger trigger){
        for (Event event : mEvents){
            if (event.trigger().equals(trigger)){
                event.execute();
            }
        }
    }
}
