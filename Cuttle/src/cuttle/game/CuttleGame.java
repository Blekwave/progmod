package cuttle.game;

import cuttle.game.actions.Action;
import cuttle.game.cards.Pile;
import cuttle.game.cards.CuttleCard;
import cuttle.game.updates.GameEndUpdate;

import java.util.HashMap;

/**
 * Describe this class and the methods exposed by it.
 */
public class CuttleGame {

    public CuttleGame(ServerInterface server){
        mPlayer = new Player(this, 0);
        mOpponent = new Player(this, 1);
        mServerAdapter = new ServerAdapter(server, this);
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

    public ServerAdapter serverAdapter(){
        return mServerAdapter;
    }

    private Player mPlayer;
    private Player mOpponent;
    private Pile mDeck;
    private Pile mScrapPile;
    private ServerAdapter mServerAdapter;

    public void startGame(){
        // Initialize deck
        mScrapPile = new Pile("scrap_pile", null);

        mCardPileMap = new HashMap<>();
        for (CuttleCard c : mDeck){
            mCardPileMap.put(c, mDeck);
        }
    }

    private Boolean isGameOver(){
        if (mPlayer.hasWon()){
            updateGameEnd(mPlayer);
            return true;
        } else if (mOpponent.hasWon()){
            updateGameEnd(mOpponent);
            return true;
        }
        return false;
    }

    private void updateGameEnd(Player player){
        GameEndUpdate update = new GameEndUpdate(player);
        mServerAdapter.update(update);
    }

    public void perform(Action action){
        action.act();
        mServerAdapter.update(action);
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
