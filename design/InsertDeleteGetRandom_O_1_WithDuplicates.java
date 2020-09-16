package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Aayush Srivastava
 */
public class InsertDeleteGetRandom_O_1_WithDuplicates {

    private Map<Integer, LinkedHashSet<Integer>> valIndexMap;
    private List<Integer> list;
    private Random rand;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandom_O_1_WithDuplicates() {
        valIndexMap = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean alreadyExists = valIndexMap.containsKey(val);
        if (!alreadyExists) {
            valIndexMap.put(val, new LinkedHashSet<>());
        }
        valIndexMap.get(val).add(list.size());
        list.add(val);
        return !alreadyExists;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!valIndexMap.containsKey(val))
            return false;
        LinkedHashSet<Integer> valSet = valIndexMap.get(val);
        int indexToReplace = valSet.iterator().next();
        int temp = list.get(list.size() - 1);
        LinkedHashSet<Integer> replaceWith = valIndexMap.get(temp);

        list.set(indexToReplace, temp);

        valSet.remove(indexToReplace);

        if (indexToReplace != list.size() - 1) {
            replaceWith.remove(list.size() - 1);
            replaceWith.add(indexToReplace);
        }

        list.remove(list.size() - 1);

        if (valSet.isEmpty()) {
            valIndexMap.remove(val);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}
