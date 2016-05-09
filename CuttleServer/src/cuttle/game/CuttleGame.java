package cuttle.game;

import cuttle.game.actions.Action;
import cuttle.game.cards.Pile;
import cuttle.game.cards.CuttleCard;
import cuttle.game.cards.deck.DeckBuilder;
import cuttle.game.cards.prompts.PlayPrompt;
import cuttle.game.updates.*;

import java.util.HashMap;

/**
 * Manages a single game of Cuttle between two players. Communicates with a
 * server which implements ServerInterface.
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

    /**
     * Initializes a game of cuttle, linking it to a server and defining the
     * ids of the players, which are used for communication with the server.
     *
     * @param server Server with which the game should communicate.
     * @param idFirst ID of the player which should go first.
     * @param idSecond ID of the player which should go second.
     */
    public CuttleGame(ServerInterface server, Integer idFirst, Integer idSecond){
        mPlayer = new Player(this, idFirst);
        mOpponent = new Player(this, idSecond);
        // Opponents are defined after construction because both of them must
        // have been initialized in order to bind these correctly.
        mPlayer.setOpponent(mOpponent);
        mOpponent.setOpponent(mPlayer);
        mServerAdapter = new ServerAdapter(server, this);
    }


    /**
     * Deals a given number of cards to a player from the deck.
     *
     * @param player Player to whom the cards should be dealt.
     * @param number Number of cards to be dealt.
     */
    private void dealCards(Player player, Integer number){
        for (Integer i = 0; i < number; i++){
            CuttleCard card = mDeck.pop();
            player.hand().push(card);
            updateCardPile(card, player.hand());
        }
    }

    private final Integer firstNumberOfCards = 5;
    private final Integer secondNumberOfCards = 6;

    /**
     * Performs some pre-game setup: initializes the deck of cards, the tie
     * timeout counter, the map between cards and their locations and deals
     * cards to each player.
     */
    private void preGamePreparations(){
        DeckBuilder deckBuilder = new DeckBuilder(this);
        mDeck = deckBuilder.buildDeck();
        mScrapPile = new Pile("scrap_pile", null);
        mTieTurnCounter = 0;

        initializeCardPileMap();

        // Deal five cards to the first player, six to the second
        dealCards(mPlayer, firstNumberOfCards);
        dealCards(mOpponent, secondNumberOfCards);
    }

    /**
     * Notifies players about the new turn.
     */
    private void newTurn(){
        NewTurnUpdate update = new NewTurnUpdate(mPlayer);
        mServerAdapter.update(update);
    }

    /**
     * Switches the current player.
     */
    private void switchPlayer(){
        Player temp = mOpponent;
        mOpponent = mPlayer;
        mPlayer = temp;
    }

    /**
     * Checks if the game is over and sends a GameEndUpdate if that's the case.
     *
     * @return Whether or not the game is over.
     */
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

    /**
     * Starts a game between the specified players.
     */
    public void start(){
        preGamePreparations();

        GameStartUpdate gameStartUpdate = new GameStartUpdate(this);
        mServerAdapter.update(gameStartUpdate);

        while (!isGameOver()){
            newTurn();
            PlayPrompt prompt = new PlayPrompt();
            prompt.prompt(mPlayer);
            if (prompt.passed()){
                mTieTurnCounter++;
            } else {
                mTieTurnCounter = 0;
            }
            switchPlayer();
        }
    }

    /**
     * Performs an action and sends an action update about it.
     *
     * @param action
     */
    public void perform(Action action){
        action.act();
        mServerAdapter.update(new ActionUpdate(action));
    }

    // CARD PILE MAP
    // The code below refers to a map between each card in the game and the pi-
    // le they belong to. It is updated by every action which changes the loca-
    // tion of a card.

    /**
     * Initializes the map between cards and their pile, assuming all cards
     * are still in the deck.
     */
    public void initializeCardPileMap(){
        mCardPileMap = new HashMap<>();
        for (CuttleCard c : mDeck){
            mCardPileMap.put(c, mDeck);
        }
    }

    /**
     * Finds which pile a card belongs to.
     *
     * @param card Card to be found.
     * @return Pile to which it belongs.
     */
    public Pile cardPile(CuttleCard card){
        return mCardPileMap.get(card);
    }

    /**
     * Updates a card's location.
     *
     * @param card Card whose location should be updated.
     * @param pile New location.
     */
    public void updateCardPile(CuttleCard card, Pile pile){
        mCardPileMap.put(card, pile);
    }

    private HashMap<CuttleCard, Pile> mCardPileMap;

}
