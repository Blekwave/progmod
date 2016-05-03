package cuttle.game;

import cuttle.game.cards.CuttleCard;

import java.util.ArrayList;

/**
 * Cuttle player.
 */
public class Player {
    private Integer mVictoryReq;
    private Integer mHandVisibility;
    private Integer mProtection;

    private ArrayList<CuttleCard> mHand;
    private ArrayList<CuttleCard> mPointBoard;
    private ArrayList<CuttleCard> mContinuousBoard;

    private CuttleGame mGame;

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
