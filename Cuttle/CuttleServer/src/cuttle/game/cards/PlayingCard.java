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
        SPADES(3, "spades"), HEARTS(2, "hearts"), DIAMONDS(1, "diamonds"), CLUBS(0, "clubs");
        private final Integer mValue;
        private final String mString;

        Suit(Integer value, String string){
            mValue = value;
            mString = string;
        }

        public Integer value(){
            return mValue;
        }

        public String string(){
            return mString;
        }
    }

    /**
     * Rank of a card.
     *
     * Exposed methods:
     * - value()
     */
    public enum Rank {
        ACE(1, "ace"), TWO(2, "two"), THREE(3, "three"), FOUR(4, "four"),
        FIVE(5, "five"), SIX(6, "six"), SEVEN(7, "seven"), EIGHT(8, "eight"),
        NINE(9, "nine"), TEN(10, "ten"), JACK(11, "jack"), QUEEN(12, "queen"),
        KING(13, "king");
        private final Integer mValue;
        private final String mString;

        Rank(Integer value, String string){
            mValue = value;
            mString = string;
        }

        public Integer value(){
            return mValue;
        }

        public String string(){
            return mString;
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
