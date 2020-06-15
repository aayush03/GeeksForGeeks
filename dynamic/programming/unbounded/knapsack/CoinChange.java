package dynamic.programming.unbounded.knapsack;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Sample Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange {

    public static void main(String[] args) {
        System.out.println("Minimum coins needed to get the target amount : " + new CoinChange().coinChange(new int[]{1, 5, 2}, 11));
        System.out.println("Minimum coins needed to get the target amount : " + new CoinChange().coinChange(new int[]{2}, 3));
    }

    /**
     * 1. Determine if the denomination that we are currently looking at is <= the current amount that we want to make change for.
     * If it is, then we can make change with it, if not we move forward.
     * <p>
     * 2. We ask ourselves: is the minimum number of coins previously found less than whatever change I can make with my amount + the amount of change
     * it takes to make with the remainder? If so, we update our value, if not we move on.
     * <p>
     * 3. If the last value in the minCoins array has not been updated we know that there wasn't a way to make change for it, so we return -1, if not
     * we return the value recorded at that index.
     * <p>
     * Also, I filled the array with amount + 1 because you would only be able to use (at most) amount number of coins to make change for amount.
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //(totalAmount + 1) can be the max value that can be attained so use (totalAmount + 1) to initialize instead of Integer.MAX_VALUE
        dp[0] = 0;

        int n = coins.length;

        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            for (int higherAmount = coin; higherAmount <= amount; higherAmount++) {//if the higher amount is less than or equal to our goal amount
                int remainderAmount = higherAmount - coin;
                dp[higherAmount] = Math.min(dp[higherAmount], 1 + dp[remainderAmount]);// The "1" stands for our current denomination.
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
