package cuttle.game.cards;

import cuttle.game.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe this class and the methods exposed by it.
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

    public Pile(String name, Player owner){
        mName = name;
        mOwner = owner;
    }

    public CuttleCard pop(Integer index){
        CuttleCard card = mList.get(index);
        mList.remove(index);
        return card;
    }

    public CuttleCard pop(){
        return pop(mList.size() - 1);
    }

    public void push(CuttleCard card){
        mList.add(card);
    }

    public CuttleCard last(){
        return mList.get(mList.size() - 1);
    }

    public Integer indexOf(CuttleCard card){
        return mList.indexOf(card);
    }

    public Iterator<CuttleCard> iterator(){
        return mList.iterator();
    }
}
