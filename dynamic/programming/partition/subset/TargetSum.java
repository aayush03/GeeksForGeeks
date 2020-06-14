package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Sample Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {

    public static void main(String[] args) {
        System.out.println("No of ways to achieve Target Sum : " + new TargetSum().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println("No of ways to achieve Target Sum : " + new TargetSum().findTargetSumWays(new int[]{1, 2, 7, 9, 981}, 1000000000));
        System.out.println("No of ways to achieve Target Sum : " + new TargetSum().findTargetSumWays(new int[]{2, 107, 109, 113, 127, 131, 137, 3, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 47, 53}, 1000));
    }

    /**
     * Similar approach as {@link CountNumberOfSubsetsWithGivenDifference} except special handling array for elements with value 0 during initialization
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000) return 0;
        int n = nums.length;
        int totalSum = 0;
        for (int i = 0; i < n; i++)
            totalSum += nums[i];

        if ((totalSum % 2 != 0 && S % 2 == 0) || (totalSum % 2 == 0 && S % 2 != 0))
            return 0;

        int targetSum = (totalSum + S) / 2;

        return subsetSum(nums, targetSum, n);
    }

    public int subsetSum(int nums[], int W, int n) {
        int[][] t = new int[n + 1][W + 1];
        t[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] == 0) //Special handling required for 0 values as choosing or not choosing 0 gives the same result
                t[i][0] = 2 * t[i - 1][0];
            else
                t[i][0] = t[i - 1][0];
        }
        for (int i = 1; i <= W; i++)
            t[0][i] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (nums[i - 1] <= j) {
                    t[i][j] = t[i - 1][j - nums[i - 1]] + t[i - 1][j];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[n][W];
    }
}
