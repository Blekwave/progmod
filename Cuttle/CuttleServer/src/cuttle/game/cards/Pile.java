package cuttle.game.cards;

import cuttle.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Container of CuttleCards. Represented internally by an ArrayList.
 */
public class Pile implements Iterable<CuttleCard> {

    public String name(){
        return mName;
    }

    public Player owner(){
        return mOwner;
    }

    private String mName;
    private Player mOwner;
    private ArrayList<CuttleCard> mList;

    /**
     * Initializes a pile of cards.
     *
     * @param name Unique string identifier for this pile.
     * @param owner Player who owns this pile (null if no player owns it, i.e.
     *              scrap pile and deck of cards)
     */
    public Pile(String name, Player owner){
        mName = name;
        mOwner = owner;
        mList = new ArrayList<>();
    }

    /**
     * Clones (shallowly) an existing pile of cards into a new one.
     *
     * @param from Pile which should be copied.
     */
    public Pile(Pile from){
        this(from.name(), from.owner());
        for (CuttleCard card : from.mList){
            mList.add(card);
        }
    }

    /**
     * Gets a card by its index.
     *
     * @param index Index of the card.
     * @return CuttleCard at the index.
     */
    public CuttleCard get(Integer index){
        return mList.get(index);
    }

    /**
     * Removes a card from the pile with a certain index and returns it.
     *
     * @param index Index to be removed.
     * @return CuttleCard removed.
     */
    public CuttleCard pop(Integer index){
        CuttleCard card = mList.get(index);
        mList.remove(index.intValue());
        return card;
    }

    /**
     * Removes a card from the top of the pile.
     *
     * @return CuttleCard removed.
     */
    public CuttleCard pop(){
        return pop(mList.size() - 1);
    }

    /**
     * Places a card at the top of the pile.
     *
     * @param card Card to be placed.
     */
    public void push(CuttleCard card){
        mList.add(card);
    }

    /**
     * Returns the number of cards in the pile.
     *
     * @return Size of the pile.
     */
    public Integer size(){
        return mList.size();
    }

    /**
     * Shuffles the pile.
     */
    public void shuffle(){
        Collections.shuffle(mList);
    }

    /**
     * Checks if the pile is empty.
     *
     * @return Whether the pile is empty.
     */
    public Boolean isEmpty(){
        return mList.isEmpty();
    }

    /**
     * Gets the index of a card in the pile (in linear time).
     *
     * @param card Card to be searched for.
     * @return Index of that card in the pile, -1 if it doesn't belong to it.
     */
    public Integer indexOf(CuttleCard card){
        return mList.indexOf(card);
    }

    /**
     * Iterator for the pile of cards.
     *
     * @return Iterator.
     */
    public Iterator<CuttleCard> iterator(){
        return mList.iterator();
    }
}
