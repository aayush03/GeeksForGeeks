package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * <p>
 * Sample Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Resultant array:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * <p>
 * Sample Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * Output: 10
 * Resultant array:
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 */
public class MaxConsecutiveOnes_III {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    public static int longestOnes(int[] A, int K) {
        int zeroCount = 0;
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < A.length; right++) {
            if (A[right] == 0)
                zeroCount++;

            while (zeroCount > K) {
                if (A[left++] == 0)
                    zeroCount--;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
