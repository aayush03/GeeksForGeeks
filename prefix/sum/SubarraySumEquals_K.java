package prefix.sum;

/**
 * @author Aayush Srivastava
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Sample Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarraySumEquals_K {

    public static void main(String[] args) {
        System.out.println("Subarray Sum Equals K : " + new SubarraySumEquals_K().subarraySum(new int[]{1, 1, 1}, 2));
    }

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        if (n == 0)
            return 0;
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
