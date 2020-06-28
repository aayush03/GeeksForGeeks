package prefix.sum;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 * <p>
 * Sample Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 */
public class FindPivotIndex {

    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{-1, -1, -1, -1, -1, 0}));
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(pivotIndexSpaceOptimized(new int[]{1, 2, 3}));
    }

    public static int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return -1;
        int[] prefix = new int[n + 1];
        int[] suffix = new int[n + 1];

        for (int i = 1; i <= n; i++)
            prefix[i] = prefix[i - 1] + nums[i - 1];

        suffix[n] = nums[n - 1];
        for (int i = n - 1; i > 0; i--)
            suffix[i] = suffix[i + 1] + nums[i - 1];

        for (int i = 1; i <= n; i++)
            if (prefix[i] == suffix[i])
                return i - 1;

        return -1;
    }

    public static int pivotIndexSpaceOptimized(int[] nums) {
        int sum = 0, leftSum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftSum == sum - leftSum - nums[i]) return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
