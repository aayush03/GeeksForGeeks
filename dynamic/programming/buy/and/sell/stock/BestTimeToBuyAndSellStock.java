package dynamic.programming.buy.and.sell.stock;

/**
 * @author Aayush Srivastava
 */

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.wo
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxCurr = 0;
        int maxSoFar = 0;
        for (int i = 1; i < n; i++) {
            maxCurr = Math.max(0, maxCurr + prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxSoFar, maxCurr);
        }

        return maxSoFar;
    }
}
