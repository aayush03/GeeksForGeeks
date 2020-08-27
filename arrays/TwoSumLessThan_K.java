package arrays;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;

/**
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 * <p>
 * Sample Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation:
 * We can use 34 and 24 to sum 58 which is less than 60.
 * <p>
 * Sample Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation:
 * In this case it's not possible to get a pair sum less that 15.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 */
public class TwoSumLessThan_K {

    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = A.length - 1;

        while (left < right) {
            int sum = A[left] + A[right];
            if (sum < K) {
                max = Math.max(max, sum);
                left++;
            } else {
                right--;
            }
        }

        return max != Integer.MIN_VALUE ? max : -1;
    }
}
