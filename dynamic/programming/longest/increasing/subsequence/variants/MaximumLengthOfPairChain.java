package dynamic.programming.longest.increasing.subsequence.variants;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 *
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 *
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Sample Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 */
public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        if (len == 0)
            return 0;
        int[] dp = new int[len];
        dp[0] = 1;

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int maxLen = 1;
        for (int i = 1; i < len; i++) {
            int v1 = pairs[i][0];
            int max = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[j][1] < v1)
                    max = Math.max(max, dp[j] + 1);
            }

            dp[i] = max;
            maxLen = Math.max(max, maxLen);
        }

        return maxLen;
    }
}
