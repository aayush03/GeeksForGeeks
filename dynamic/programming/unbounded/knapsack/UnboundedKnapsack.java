package dynamic.programming.unbounded.knapsack;

/**
 * @author Aayush Srivastava
 */

/**
 * Given weights and values related to n items and the maximum capacity allowed for these items.
 * What is the maximum value we can achieve if we can pick any weights any number of times for a total allowed weight of W?
 */
public class UnboundedKnapsack {
    public static void main(String[] args) {
        System.out.println(new UnboundedKnapsack().unboundedKnapsack(new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 8, 4));
        System.out.println(new UnboundedKnapsack().unboundedKnapsackSpaceOptimized(new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 8, 4));
    }

    private int unboundedKnapsack(int[] wt, int[] val, int W, int n) {
        int[][] t = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] <= j)
                    t[i][j] = Math.max(val[i - 1] + t[i][j - wt[i - 1]], t[i - 1][j]);
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][W];
    }

    private int unboundedKnapsackSpaceOptimized(int[] wt, int[] val, int W, int n) {
        int dp[] = new int[W + 1];

        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= i) {
                    dp[i] = Math.max(dp[i], dp[i - wt[j]] + val[j]);
                }
            }
        }
        return dp[W];
    }

}
