package cuttle.game;

import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.behaviors.PlayerBehavior;

import java.util.ArrayList;

/**
 * Cuttle player.
 */
public class Player {
    private Integer mVictoryReq;
    private Integer mHandVisibility;
    private Integer mProtection;

    public ArrayList<CuttleCard> hand(){
        return mHand;
    }

    public ArrayList<CuttleCard> pointBoard() {
        return mPointBoard;
    }

    public ArrayList<CuttleCard> continuousBoard() {
        return mContinuousBoard;
    }

    private ArrayList<CuttleCard> mHand;
    private ArrayList<CuttleCard> mPointBoard;
    private ArrayList<CuttleCard> mContinuousBoard;

    private CuttleGame mGame;

    private ArrayList<PlayerBehavior> mBehaviors;

    public Player(CuttleGame game){
        mVictoryReq = game.victoryReq();
        mHandVisibility = 0;
        mProtection = 0;

        mHand = new ArrayList<>();
        mPointBoard = new ArrayList<>();
        mContinuousBoard = new ArrayList<>();

        mGame = game;
    }

}
