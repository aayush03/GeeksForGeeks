package dynamic.programming.longest.increasing.subsequence.variants;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another
 * if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * <p>
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * <p>
 * Note:
 * Rotation is not allowed.
 * <p>
 * Sample Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{
                {5, 4}, {6, 4}, {6, 7}, {2, 3}
        }));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 0)
            return 0;
        Arrays.sort(envelopes, (e1, e2) -> e1[0] - e2[0]);
        int[] dp = new int[len];
        dp[0] = 1;
        int maxEnvelopes = 1;
        for (int i = 1; i < len; i++) {
            int max = 1;
            int currWidth = envelopes[i][0];
            int currHeight = envelopes[i][1];
            for (int j = i - 1; j >= 0; j--) {
                if (currWidth > envelopes[j][0] && currHeight > envelopes[j][1])
                    max = Math.max(max, dp[j] + 1);
            }

            dp[i] = max;
            maxEnvelopes = Math.max(max, maxEnvelopes);
        }

        return maxEnvelopes;
    }
}
