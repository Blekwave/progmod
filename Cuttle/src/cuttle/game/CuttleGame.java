package cuttle.game;

import cuttle.game.cards.CuttleCard;

import java.util.ArrayList;

/**
 * Describe this class and the methods exposed by it.
 */
public class CuttleGame {
    private final Integer mVictoryReq = 21;

    private Player mPlayer;
    private Player mOpponent;

    private ArrayList<CuttleCard> mDeck;
    private ArrayList<CuttleCard> mScrapPile;

    public Integer victoryReq(){
        return mVictoryReq;
    }

    public void startGame(){
        // ...
    }

    private Boolean checkWinCondition(){
        // ...
        return false;
    }
}
