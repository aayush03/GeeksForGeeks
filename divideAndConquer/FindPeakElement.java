package divideAndConquer;

/**
 * @author Aayush Srivastava
 */

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * Follow up: Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {

    /**
     * If num[i-1] < num[i] > num[i+1], then num[i] is peak
     * If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
     * If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
     * If num[i-1] > num[i] < num[i+1], then both sides have peak
     * (n is num.length)
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (left + 1 == right)
                return nums[left] > nums[right] ? left : right;
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            else if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}
