package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Aayush Srivastava
 */
public class InsertDeleteGetRandom_O_1 {

    private Map<Integer, Integer> valIndexMap;
    private List<Integer> list;
    private Random rand;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom_O_1() {
        valIndexMap = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valIndexMap.containsKey(val))
            return false;
        list.add(val);
        valIndexMap.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valIndexMap.containsKey(val))
            return false;
        int index = valIndexMap.get(val);
        int temp = list.get(list.size() - 1);
        list.set(index, temp);
        list.remove(list.size() - 1);
        valIndexMap.put(temp, index);
        valIndexMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}
