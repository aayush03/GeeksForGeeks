package prefix.sum;

/**
 * @author Aayush Srivastava
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Sample Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println("Length of longest contiguous subarray with equal number of 0 and 1 : " + new ContiguousArray().findMaxLength(new int[]{}));
        System.out.println("Length of longest contiguous subarray with equal number of 0 and 1 : " + new ContiguousArray().findMaxLength(new int[]{0, 1}));
        System.out.println("Length of longest contiguous subarray with equal number of 0 and 1 : " + new ContiguousArray().findMaxLength(new int[]{0, 1, 0}));
        System.out.println("Length of longest contiguous subarray with equal number of 0 and 1 : " + new ContiguousArray().findMaxLength(new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println("Length of longest contiguous subarray with equal number of 0 and 1 : " + new ContiguousArray().findMaxLength(new int[]{0, 0, 1, 1, 0, 0, 1}));
    }

    public int findMaxLength(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0)
                nums[i] = -1;
            if (i > 0)
                prefix[i] = nums[i - 1] + prefix[i - 1];
        }

        prefix[n] = nums[n - 1] + prefix[n - 1];


        Map<Integer, Integer> map = new HashMap<>();

        int maxLen = 0;

        for (int i = 0; i <= n; i++) {
            int sum = prefix[i];

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }

        }

        return maxLen;

    }
}
