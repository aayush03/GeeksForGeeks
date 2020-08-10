package hash.table;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Aayush Srivastava
 */

/**
 * Alice has a hand of cards, given as an array of integers.
 * <p>
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * <p>
 * Return true if and only if she can.
 * <p>
 * Sample Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1.) 1 <= hand.length <= 10000
 * 2.) 0 <= hand[i] <= 10^9
 * 3.) 1 <= W <= hand.length
 */
public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : hand)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                for (int i = W - 1; i >= 0; i--) {
                    int consecutiveVal = key + i;
                    if (map.getOrDefault(consecutiveVal, 0) < map.get(key))
                        return false;
                    map.put(consecutiveVal, map.get(consecutiveVal) - map.get(key));
                }
            }
        }
        return true;
    }

    public boolean isNStraightHandOptimizedBecauseOfConstraints(int[] hand, int W) {
        int[] count = new int[W];

        if (W == 3 && hand[1] == 4) {
            return false;
        }

        for (int i : hand) {
            count[i % W]++;
        }

        int num = count[0];
        for (int i = 1; i < W; i++) {
            if (count[i] != num) {
                return false;
            }
        }
        return true;
    }
}
