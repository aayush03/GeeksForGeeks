package dynamic.programming.longest.increasing.subsequence.variants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Sample Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 */
public class LargestDivisibleSubset {

    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{2,4,7,8}));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int len = nums.length;
        if (len == 0) return res;

        // Initialize dp.
        int[] dp = new int[len];
        dp[0] = 1;
        Arrays.sort(nums);
        int maxVal = 1;
        int index = 0;
        for (int i = 1; i < len; i++) {
            int cur = nums[i];
            // Be careful.
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (cur % nums[j] == 0) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            if (max > maxVal) {
                maxVal = max;
                index = i;
            }
        }


        int last = nums[index];
        res.add(last);
        maxVal--;
        for (int i = index - 1; i >= 0; i--) {
            if (last % nums[i] == 0 && dp[i] == maxVal) {
                res.add(nums[i]);
                maxVal--;
            }
        }

        return res;
    }
}
