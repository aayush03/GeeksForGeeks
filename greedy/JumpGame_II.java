package greedy;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame_II {

    public int jump(int[] nums) {
        int i = 0;
        int curFarthest = 0;
        int curEnd = 0;
        int count = 0;
        while (i < nums.length - 1) {
            curFarthest = Math.max(i + nums[i], curFarthest);
            if (curFarthest >= nums.length - 1) {
                count++;
                break;
            }

            if (i == curEnd) {
                count++;
                curEnd = curFarthest;
            }
            i++;
        }

        return count;
    }
}
