package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */
public class Knapsack_0_1 {

    public static void main(String[] args) {
        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};
        int W = 7;
        int n = wt.length;
        System.out.println(" Max value in Knapsack using recursion : " + new Knapsack_0_1().knapsack(wt, val, W, n));
        System.out.println(" Max value in Knapsack using memoization : " + new Knapsack_0_1().knapsackMemoization(wt, val, W, n));
        System.out.println(" Max value in Knapsack using tabulation : " + new Knapsack_0_1().knapsackTabulation(wt, val, W, n));
    }

    private int knapsack(int[] wt, int[] val, int W, int n) {
        if (n == 0 || W == 0)
            return 0;

        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1), //choose this
                    knapsack(wt, val, W, n - 1)); // don't choose this

        return knapsack(wt, val, W, n - 1); // default option since this item can't be chosen because its weight is more than total weight allowed
    }

    int[][] memo;

    private int knapsackMemoization(int[] wt, int[] val, int W, int n) {
        initializeMemoTable(W, n);
        return knapsackMemo(wt, val, W, n);
    }

    private int knapsackMemo(int[] wt, int[] val, int W, int n) {
        if (memo[n][W] != -1) //implies that value has already been memoized
            return memo[n][W];

        if (wt[n - 1] <= W)
            return memo[n][W] = Math.max(val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1), //choose this
                    knapsack(wt, val, W, n - 1)); // don't choose this

        return memo[n][W] = knapsack(wt, val, W, n - 1); // default option since this item can't be chosen because its weight is more than total weight allowed
    }

    private void initializeMemoTable(int W, int n) {
        memo = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= W; j++)
                memo[i][j] = -1;
    }

    private int knapsackTabulation(int[] wt, int[] val, int W, int n) {
        int[][] t = new int[n + 1][W + 1];

        //replaced "n" with "i" and "W" with "j" in iterative approach converted from recursion / memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] <= j)
                    t[i][j] = Math.max(val[i - 1] + t[i - 1][j - wt[i - 1]],
                            t[i - 1][j]);
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][W];
    }

}
