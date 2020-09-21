package design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * <p>
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * LFUCache cache = new LFUCache( 2 |* capacity *| );
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class LFUCache {

    private Map<Integer, Integer> keyValueMap;
    private Map<Integer, Integer> keyFrequencyMap;
    private Map<Integer, LinkedHashSet<Integer>> frequencyKeyOrderMap;
    private int capacity;
    private int minimumFrequency = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyValueMap = new HashMap<>();
        this.keyFrequencyMap = new HashMap<>();
        this.frequencyKeyOrderMap = new HashMap<>();
        this.frequencyKeyOrderMap.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!keyValueMap.containsKey(key))
            return -1;
        int freq = keyFrequencyMap.get(key);
        keyFrequencyMap.put(key, freq + 1);
        frequencyKeyOrderMap.get(freq).remove(key);

        if (freq == minimumFrequency && frequencyKeyOrderMap.get(freq).size() == 0)
            minimumFrequency++;
        if (!frequencyKeyOrderMap.containsKey(freq + 1))
            frequencyKeyOrderMap.put(freq + 1, new LinkedHashSet<>());

        frequencyKeyOrderMap.get(freq + 1).add(key);

        return keyValueMap.get(key);
    }

    public void put(int key, int value) {
        if (keyValueMap.containsKey(key)) {
            keyValueMap.put(key, value);
            get(key);
            return;
        }

        if (keyValueMap.size() >= capacity) {
            int keyToBeEvicted = frequencyKeyOrderMap.get(minimumFrequency).iterator().next();
            frequencyKeyOrderMap.get(minimumFrequency).remove(keyToBeEvicted);
            keyValueMap.remove(keyToBeEvicted);
            keyFrequencyMap.remove(keyToBeEvicted);
        }

        keyValueMap.put(key, value);
        keyFrequencyMap.put(key, 1);
        minimumFrequency = 1;
        frequencyKeyOrderMap.get(1).add(key);
    }
}
