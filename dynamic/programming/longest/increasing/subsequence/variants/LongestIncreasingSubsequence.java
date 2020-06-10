package dynamic.programming.longest.increasing.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

import java.util.stream.IntStream;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Sample Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    private static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        // Initialize dp.
        int[] dp = new int[len];
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            int cur = nums[i];
            // Be careful.
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (cur > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        // Think about [2, 8, 9, 3]
        return IntStream.of(dp).max().orElse(0);
    }
}
