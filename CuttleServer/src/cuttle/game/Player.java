package cuttle.game;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.behaviors.PlayerBehavior;
import cuttle.game.cards.behaviors.playerbehaviors.DiscardBehavior;
import cuttle.game.cards.behaviors.playerbehaviors.DrawBehavior;
import cuttle.game.cards.behaviors.playerbehaviors.ImmediateDiscardBehavior;
import cuttle.game.cards.behaviors.playerbehaviors.PassBehavior;

import java.util.ArrayList;

/**
 * Player in a game of Cuttle. Three card Piles belong to it: hand, point board
 * and continuous board. It also has a list of behaviors, in regard to plays
 * such as drawing a card or not doing anything.
 */
public class Player {

    public Integer id(){
        return mId;
    }

    public Pile hand(){
        return mHand;
    }

    public Pile pointBoard() {
        return mPointBoard;
    }

    public Pile continuousBoard() {
        return mContinuousBoard;
    }

    public CuttleGame game(){
        return mGame;
    }

    public ArrayList<PlayerBehavior> behaviors(){
        return mBehaviors;
    }

    public ImmediateDiscardBehavior immediateDiscardBehavior(){
        return mImmediateDiscardBehavior;
    }

    public Player opponent(){
        return mOpponent;
    }

    public void setOpponent(Player opponent){
        mOpponent = opponent;
    }

    public BehaviorIterator behaviorIterator(){
        return new BehaviorIterator(this);
    }

    private Integer mId;
    private Pile mHand;
    private Pile mPointBoard;
    private Pile mContinuousBoard;
    private CuttleGame mGame;
    private Player mOpponent;

    private ArrayList<PlayerBehavior> mBehaviors;
    private ImmediateDiscardBehavior mImmediateDiscardBehavior;

    /**
     * Initializes a new player.
     *
     * @param game Game to which this player belongs.
     * @param id Numeric identifier supplied by the server.
     */
    public Player(CuttleGame game, Integer id){
        mId = id;

        mKingCount = 0;
        mQueenCount = 0;
        mEightCount = 0;

        mHand = new Pile("p" + id + "_hand", this);
        mPointBoard = new Pile("p" + id + "_point_board", this);
        mContinuousBoard = new Pile("p" + id + "_continuous_board", this);

        mGame = game;

        mBehaviors = new ArrayList<>();
        mBehaviors.add(new DrawBehavior(this));
        mBehaviors.add(new DiscardBehavior(this));
        mBehaviors.add(new PassBehavior(this));
        mImmediateDiscardBehavior = new ImmediateDiscardBehavior(this);
    }

    /**
     * Checks if this player has enough points to win the game.
     *
     * @return Whether or not it has won the game.
     */
    public Boolean hasWon(){
        Integer pointSum = 0;
        for (CuttleCard card : mPointBoard){
            pointSum += card.rank().value();
        }
        return pointSum >= victoryReq();
    }

    /**
     * Checks if this player's hand should be visible to the other player.
     *
     * A player's hand is visible if the opposing player has an eight in its
     * continuous board.
     *
     * @return Whether its hand is visible.
     */
    public Boolean handIsVisible(){
        return mEightCount > 0;
    }

    public void raiseVisibility(){
        mEightCount++;
    }

    public void lowerVisibility(){
        mEightCount--;
    }

    /**
     * Checks if this player is protected against targeted spells.
     *
     * A player is protected against targeted spells if it has a queen in its
     * continuous board.
     *
     * @return Whether this player is protected.
     */
    public Boolean isProtected(){
        return mQueenCount > 0;
    }

    public void raiseProtection(){
        mQueenCount++;
    }

    public void lowerProtection(){
        mQueenCount--;
    }

    /**
     * Computes how many points are required for this player to win.
     *
     * A player's win requirements depend on the number of kings on board.
     * None: 21, 1 king: 14, 2 kings: 10, 3 kings: 7, all 4 kings: 5
     *
     * @return Victory requirements for this player.
     */
    public Integer victoryReq(){
        switch (mKingCount){
            case 0:
                return 21;
            case 1:
                return 14;
            case 2:
                return 10;
            case 3:
                return 7;
            case 4:
                return 5;
            default:
                return -1; // TODO: throw an error
        }
    }

    public void raiseVictoryReq(){
        mKingCount++;
    }

    public void lowerVictoryReq(){
        mKingCount--;
    }

    private Integer mEightCount;
    private Integer mQueenCount;
    private Integer mKingCount;

}
