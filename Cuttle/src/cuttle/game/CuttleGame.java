package cuttle.game;

import cuttle.game.actions.Action;
import cuttle.game.cards.Pile;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.deck.DeckBuilder;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.updates.*;

import java.util.HashMap;

/**
 * Describe this class and the methods exposed by it.
 */
public class CuttleGame {

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
    private Player mWinner;

    public CuttleGame(ServerInterface server, Integer idFirst, Integer idSecond){
        mPlayer = new Player(this, idFirst, mOpponent);
        mOpponent = new Player(this, idSecond, mPlayer);
        mServerAdapter = new ServerAdapter(server, this);

        start();
    }

    private void preGamePreparations(){
        DeckBuilder deckBuilder = new DeckBuilder(this);
        mDeck = deckBuilder.buildDeck();
        mDeck.shuffle();
        mScrapPile = new Pile("scrap_pile", null);

        // All cards begin inside the deck
        mCardPileMap = new HashMap<>();
        for (CuttleCard c : mDeck){
            mCardPileMap.put(c, mDeck);
        }
    }

    private Integer start(){
        preGamePreparations();

        GameStartUpdate gameStartUpdate = new GameStartUpdate(player());
        mServerAdapter.update(gameStartUpdate);

        while (!isGameOver()){
            newTurn();
            new PlayPrompt().prompt(mPlayer);
            switchPlayer();
        }

        return mWinner.id();
    }

    private void newTurn(){
        NewTurnUpdate update = new NewTurnUpdate(mPlayer);
        mServerAdapter.update(update);
    }

    private void switchPlayer(){
        Player temp = mOpponent;
        mOpponent = mPlayer;
        mPlayer = temp;
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
        mWinner = player;
    }

    public void perform(Action action){
        action.act();
        mServerAdapter.update(new ActionUpdate(action));
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
