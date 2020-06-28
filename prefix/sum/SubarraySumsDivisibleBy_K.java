package prefix.sum;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * <p>
 * Sample Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 1 <= A.length <= 30000
 * 2.) -10000 <= A[i] <= 10000
 * 3.) 2 <= K <= 10000
 */
public class SubarraySumsDivisibleBy_K {

    public static void main(String[] args) {
        System.out.println("SubArray Sums Divisible By K : " + new SubarraySumsDivisibleBy_K().subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 3));
        System.out.println("SubArray Sums Divisible By K : " + new SubarraySumsDivisibleBy_K().subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    public int subarraysDivByK(int[] A, int K) {
        int n = A.length;

        if (n == 0)
            return 0;

        int cumulativeSum = 0;
        int[] mod = new int[K];

        for (int i = 0; i < n; i++) {
            cumulativeSum += A[i];
            mod[(((cumulativeSum % K) + K) % K)]++; // double modulus to ensure positive value of modulus even if cumulativeSum becomes -ve || refer https://www.youtube.com/watch?v=2rbeCUMBYgk
        }

        int count = 0;

        /**
         * Now that we have created the dictionary with frequency count of all the possible modulus values,
         * the possible SubArrays can be between the points where the (prefix sum) modulus value is equal, i.e
         * where mod[i] > 0. Then to choose any two such subArrays we have nC2 ways = n * (n - 1) / 2
         */
        for (int i = 0; i < K; i++) {
            if (mod[i] > 0)
                count += (mod[i] * (mod[i] - 1)) / 2;
        }

        /**
         * This is done to add all the individual values in the original array which were directly divisible by K
         */
        count += mod[0];

        return count;
    }
}
