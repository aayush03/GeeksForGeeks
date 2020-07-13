package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Sample Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * <p>
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopK_FrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if (k == n)
            return nums;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            pq.offer(e.getKey());
        }

        int[] res = new int[k];

        for (int i = k - 1; i >= 0; i--)
            res[i] = pq.poll();

        return res;
    }
}
