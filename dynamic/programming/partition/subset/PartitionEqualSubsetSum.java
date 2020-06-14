package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * <p>
 * 1. Each of the array element will not exceed 100.
 * 2. The array size will not exceed 200.
 * <p>
 * Sample Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(new PartitionEqualSubsetSum().canPartition(new int[]{1, 2, 5}));
    }


    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];

        if (sum % 2 != 0)
            return false;
        else
            sum = sum / 2;

        return hasSubsetWithGivenSum(nums, nums.length, sum);
    }

    private boolean hasSubsetWithGivenSum(int[] arr, int n, int sum) {
        boolean[][] t = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0)
                    t[0][j] = false;
                if (j == 0)
                    t[i][0] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    t[i][j] = t[i - 1][j - arr[i - 1]] || t[i - 1][j];
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][sum];
    }


}
