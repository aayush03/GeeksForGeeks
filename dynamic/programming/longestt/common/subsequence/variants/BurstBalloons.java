package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * 1.) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 2.) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * Sample Input: [3,1,5,8]
 * Output: 167
 * <p>
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    public static void main(String[] args) {
        System.out.println("Maximum numbers of coins that can be collected : " + new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }

    public int maxCoins(int[] nums) {
        return solve(nums);
    }

    int[][] memo;

    private int solve(int[] nums) {
        int n = nums.length + 2;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[n - 1] = 1;
        int k = 0;
        for (int i = 1; i < n - 1; i++)
            arr[i] = nums[k++];
        initializeMemoTable(n);
        return solve(arr, 1, arr.length - 1);
    }

    private int solve(int[] arr, int i, int j) {
        if (i >= j)
            return memo[i][j] = 0;
        if (memo[i][j] != -1)
            return memo[i][j];

        int maxCoins = Integer.MIN_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int maxCoinsByBurstingLeftSide = memo[i][k] != -1 ? memo[i][k] : solve(arr, i, k);
            int maxCoinsByBurstingRightSide = memo[k + 1][j] != -1 ? memo[k + 1][j] : solve(arr, k + 1, j);
            int tempResult = maxCoinsByBurstingLeftSide +
                    maxCoinsByBurstingRightSide +
                    (arr[i - 1] * arr[k] * arr[j]);
            maxCoins = Math.max(maxCoins, tempResult);
        }

        return memo[i][j] = maxCoins;
    }

    private void initializeMemoTable(int n) {
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                memo[i][j] = -1;
    }
}
