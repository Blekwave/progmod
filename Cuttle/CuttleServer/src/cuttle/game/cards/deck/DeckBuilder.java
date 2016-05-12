package cuttle.game.cards.deck;

import cuttle.game.CuttleGame;
import cuttle.game.cards.Pile;
import cuttle.game.cards.PlayingCard;

/**
 * Builds a deck of regular Cuttle.
 */
public class DeckBuilder {

    private CuttleGame mGame;

    /**
     * Initializes the deck builder.
     *
     * @param game Game to which the builder belongs.
     */
    public DeckBuilder(CuttleGame game){
        mGame = game;
    }

    /**
     * Builds a regular deck of Cuttle.
     *
     * @return The deck, shuffled.
     */
    public Pile buildDeck(){
        Pile deck = new Pile("deck", null);
        Integer id = 0;
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()){
            deck.push(new Ace(mGame, id++, suit));
            deck.push(new Two(mGame, id++, suit));
            deck.push(new Three(mGame, id++, suit));
            deck.push(new Four(mGame, id++, suit));
            deck.push(new Five(mGame, id++, suit));
            deck.push(new Six(mGame, id++, suit));
            deck.push(new Seven(mGame, id++, suit));
            deck.push(new Eight(mGame, id++, suit));
            deck.push(new Nine(mGame, id++, suit));
            deck.push(new Ten(mGame, id++, suit));
            deck.push(new Jack(mGame, id++, suit));
            deck.push(new Queen(mGame, id++, suit));
            deck.push(new King(mGame, id++, suit));
        }
        deck.shuffle();
        return deck;
    }
}
