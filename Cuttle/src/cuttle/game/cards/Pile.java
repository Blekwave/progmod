package cuttle.game.cards;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe this class and the methods exposed by it.
 */
public class Pile implements Iterable<CuttleCard> {

    public String name(){
        return mName;
    }

    private String mName;
    private ArrayList<CuttleCard> mList;

    public Pile(String name){
        mName = name;
    }

    public CuttleCard pop(){
        CuttleCard top = mList.get(mList.size() - 1);
        mList.remove(mList.size() - 1);
        return top;
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
