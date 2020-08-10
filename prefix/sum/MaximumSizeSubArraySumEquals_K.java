package prefix.sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Sample Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 */
public class MaximumSizeSubArraySumEquals_K {

    public static void main(String[] args) {
        System.out.println("Maximum Size SubArray Sum Equals K : " + new MaximumSizeSubArraySumEquals_K().maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        System.out.println("Maximum Size SubArray Sum Equals K : " + new MaximumSizeSubArraySumEquals_K().maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
    }

    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;

        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); //map to maintain count corresponding to every index
        map.put(0, -1);//initialize with sum 0 index -1
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            //we will be storing sum so far for every index and if we find a (sum - k) key which is already present
            // in the map then that value will give sum from its index to current index  -> i as k
            //The final task is to find the max length between any two such indices
            if (map.containsKey(sum - k))
                res = Math.max(res, i - map.get(sum - k));
            map.putIfAbsent(sum, i);
        }

        return res;
    }
}
