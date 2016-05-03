package cuttle.game.cards;

/**
 * Playing card, defined by its suit and rank.
 *
 * This class also defines the Enums Suit and Rank.
 */
public class PlayingCard {

    /**
     * Suit of a card.
     *
     * Exposed methods:
     * - value()
     */
    public enum Suit {
        SPADES(3), HEARTS(2), DIAMONDS(1), CLUBS(0);
        private final Integer mValue;

        Suit(Integer value){
            mValue = value;
        }

        public Integer value(){
            return mValue;
        }
    }

    /**
     * Rank of a card.
     *
     * Exposed methods:
     * - value()
     */
    public enum Rank {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
        private final Integer mValue;

        Rank(Integer value){
            mValue = value;
        }

        public Integer value(){
            return mValue;
        }
    }

    private final Suit mSuit;
    private final Rank mRank;

    /**
     * Initializes a playing card.
     *
     * @param suit Suit of the card
     * @param rank Rank of the card
     */
    public PlayingCard(Suit suit, Rank rank){
        mSuit = suit;
        mRank = rank;
    }

    public Suit suit(){
        return mSuit;
    }

    public Rank rank(){
        return mRank;
    }
}
