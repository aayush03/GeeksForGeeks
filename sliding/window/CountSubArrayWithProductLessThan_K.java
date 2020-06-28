package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 * <p>
 * Sample Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 0 < nums.length <= 50000.
 * 2.) 0 < nums[i] < 1000.
 * 3.) 0 <= k < 10^6.
 */
public class CountSubArrayWithProductLessThan_K {

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int count = 0;
        int prod = 1;
        int left = 0;
        for (int right = 0; right < n; right++) {
            prod *= nums[right];

            while (left <= right && prod >= k)
                prod /= nums[left++];
            count += right - left + 1;
        }

        return count;
    }
}
