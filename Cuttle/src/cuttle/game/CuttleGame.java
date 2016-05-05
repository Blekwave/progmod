package cuttle.game;

import cuttle.game.cards.Pile;
import cuttle.game.cards.CuttleCard;

import java.util.HashMap;

/**
 * Describe this class and the methods exposed by it.
 */
public class CuttleGame {

    public CuttleGame(){
        mPlayer = new Player(this, 0);
        mOpponent = new Player(this, 1);
        mServerInterface = new ServerInterface();
    }

    public Player player(){
        return mPlayer;
    }

    public Player opponent(){
        return mOpponent;
    }

    public Pile deck(){
        return mDeck;
    }

    public Pile scrapPile(){
        return mScrapPile;
    }

    public ServerInterface serverInterface(){
        return mServerInterface;
    }

    private Player mPlayer;
    private Player mOpponent;
    private Pile mDeck;
    private Pile mScrapPile;
    private ServerInterface mServerInterface;

    public void startGame(){
        // Initialize deck
        mScrapPile = new Pile("scrap_pile");

        mCardPileMap = new HashMap<>();
        for (CuttleCard c : mDeck){
            mCardPileMap.put(c, mDeck);
        }
    }

    private Boolean checkWinCondition(){
        // ...
        return false;
    }

    // CARD LOCATION ATTRIBUTES AND METHODS

    public Pile cardPile(CuttleCard card){
        return mCardPileMap.get(card);
    }

    public void updateCardPile(CuttleCard card, Pile pile){
        mCardPileMap.put(card, pile);
    }

    private HashMap<CuttleCard, Pile> mCardPileMap;

}
