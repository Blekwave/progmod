package cuttle.game;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.Pile;
import cuttle.game.cards.behaviors.PlayerBehavior;

import java.util.ArrayList;

/**
 * Cuttle player.
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

    public BehaviorIterator behaviorIterator(){
        return new BehaviorIterator(this);
    }

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

    private Integer mId;
    private Pile mHand;
    private Pile mPointBoard;
    private Pile mContinuousBoard;
    private CuttleGame mGame;
    private ArrayList<PlayerBehavior> mBehaviors;

    public Player(CuttleGame game, Integer id){
        mId = id;

        mKingCount = 0;
        mQueenCount = 0;
        mEightCount = 0;

        mHand = new Pile("p" + id + "_hand");
        mPointBoard = new Pile("p" + id + "_point_board");
        mContinuousBoard = new Pile("p" + id + "_continuous_board");

        mGame = game;
    }

    // CONTINUOUS CARD FLAGS

    public void raiseVisibility(){
        mEightCount++;
    }

    public void lowerVisibility(){
        mEightCount--;
    }

    public void raiseProtection(){
        mQueenCount++;
    }

    public void lowerProtection(){
        mQueenCount--;
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
