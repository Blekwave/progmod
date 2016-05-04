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

    private Integer mId;

    private Integer mVictoryReq;
    private Integer mHandVisibility;
    private Integer mProtection;

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

    private Pile mHand;
    private Pile mPointBoard;
    private Pile mContinuousBoard;

    private CuttleGame mGame;

    private ArrayList<PlayerBehavior> mBehaviors;

    public Player(CuttleGame game, Integer id){
        mId = id;

        mVictoryReq = CuttleGame.victoryReq;
        mHandVisibility = 0;
        mProtection = 0;

        mHand = new Pile("p" + id + "_hand");
        mPointBoard = new Pile("p" + id + "_point_board");
        mContinuousBoard = new Pile("p" + id + "_continuous_board");

        mGame = game;
    }

}
