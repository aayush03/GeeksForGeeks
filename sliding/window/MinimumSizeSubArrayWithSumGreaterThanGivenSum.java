package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * Sample Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubArrayWithSumGreaterThanGivenSum {

    public static void main(String[] args) {
        System.out.println("Minimum Size of Sub Array With Sum Greater Than Given Sum : " + new MinimumSizeSubArrayWithSumGreaterThanGivenSum().minSubArrayLen(8, new int[]{4, 2, 2, 7, 8, 1, 2, 8, 10}));
        System.out.println("Minimum Size of Sub Array With Sum Greater Than Given Sum : " + new MinimumSizeSubArrayWithSumGreaterThanGivenSum().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int targetSum, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int currSum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            currSum += nums[right];
            while (currSum >= targetSum) {
                minLen = Math.min(minLen, right - left + 1);
                /**
                 * Shrink the window from left side
                 */
                currSum -= nums[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
