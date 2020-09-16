package dynamic.programming.buy.and.sell.stock;

/**
 * @author Aayush Srivastava
 */

/**
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 */
public class BestTimeToBuyAndSellStock_II {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int i = 0;
        int profit = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] > prices[i + 1]) {
                i++;
            }
            int buy = prices[i];

            while (i < n - 1 && prices[i] < prices[i + 1]) {
                i++;
            }
            int sell = prices[i];

            profit += (sell - buy);
            i++;
        }

        return profit;
    }
}
