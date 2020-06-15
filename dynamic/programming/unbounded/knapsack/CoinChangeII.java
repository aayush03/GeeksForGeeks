package dynamic.programming.unbounded.knapsack;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 * <p>
 * Sample Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CoinChangeII {

    public static void main(String[] args) {
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().change(5, new int[]{1, 2, 5}));
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().coinChangeOptimized(5, new int[]{1, 2, 5}));
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().change(3, new int[]{2}));
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().changeOptimized(3, new int[]{2}));
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().change(10, new int[]{10}));
        System.out.println("Total Number of ways to get the amount : " + new CoinChangeII().changeOptimized(10, new int[]{10}));
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] t = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            t[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j)
                    t[i][j] = t[i][j - coins[i - 1]] + t[i - 1][j];
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][amount];
    }

    public int coinChangeOptimized(int totalAmount, int[] coins) {
        int[] dp = new int[totalAmount + 1];
        dp[0] = 1;

        int n = coins.length;

        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            for (int higherAmount = coin; higherAmount <= totalAmount; higherAmount++) {
                int remainderAmount = higherAmount - coin;
                dp[higherAmount] = dp[higherAmount] + dp[remainderAmount];
            }
        }

        return dp[totalAmount];
    }

    private int changeOptimized(int amount, int[] coins) {
        int n = coins.length;
        if (n == 0) {
            if (amount == 0)
                return 1;
            return 0;
        }
        int[] ans = new int[amount + 1];
        ans[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                ans[j] += ans[j - coins[i]];
            }
        }

        return ans[amount];
    }
}
