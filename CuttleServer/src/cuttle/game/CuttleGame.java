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

    private Integer mTieTurnCounter;
    private final Integer mTieTurns = 6;

    private void increaseTieTurnCounter(){
        mTieTurnCounter++;
    }

    private void resetTieTurnCounter(){
        mTieTurnCounter = 0;
    }

    public CuttleGame(ServerInterface server, Integer idFirst, Integer idSecond){
        mPlayer = new Player(this, idFirst);
        mOpponent = new Player(this, idSecond);
        // Opponents are defined after construction because both of them must
        // have been initialized in order to bind these correctly.
        mPlayer.setOpponent(mOpponent);
        mOpponent.setOpponent(mPlayer);
        mServerAdapter = new ServerAdapter(server, this);
    }

    private final Integer firstNumberOfCards = 5;
    private final Integer secondNumberOfCards = 6;

    private void dealCards(Player player, Integer number){
        for (Integer i = 0; i < number; i++){
            CuttleCard card = mDeck.pop();
            player.hand().push(card);
            updateCardPile(card, player.hand());
        }
    }

    private void preGamePreparations(){
        DeckBuilder deckBuilder = new DeckBuilder(this);
        mDeck = deckBuilder.buildDeck();
        mScrapPile = new Pile("scrap_pile", null);
        mTieTurnCounter = 0;

        // All cards begin inside the deck
        mCardPileMap = new HashMap<>();
        for (CuttleCard c : mDeck){
            mCardPileMap.put(c, mDeck);
        }

        // Deal five cards to the first player, six to the second
        dealCards(mPlayer, firstNumberOfCards);
        dealCards(mOpponent, secondNumberOfCards);
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
        GameEndUpdate update = null;
        if (mPlayer.hasWon()){
            update = new GameEndUpdate(mPlayer);
        } else if (mOpponent.hasWon()){
            update = new GameEndUpdate(mOpponent);
        } else if (mTieTurnCounter >= mTieTurns) {
            update = new GameEndUpdate();
        }
        if (update != null){
            mServerAdapter.update(update);
            return true;
        }
        return false;
    }

    public void start(){
        preGamePreparations();

        GameStartUpdate gameStartUpdate = new GameStartUpdate(this);
        mServerAdapter.update(gameStartUpdate);

        while (!isGameOver()){
            newTurn();
            PlayPrompt prompt = new PlayPrompt();
            prompt.prompt(mPlayer);
            if (prompt.passed()){
                increaseTieTurnCounter();
            } else {
                resetTieTurnCounter();
            }
            switchPlayer();
        }
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
