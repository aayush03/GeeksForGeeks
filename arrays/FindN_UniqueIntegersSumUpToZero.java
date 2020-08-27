package arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: [-1,0,1]
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
 */
public class FindN_UniqueIntegersSumUpToZero {

    /**
     * Intuition
     * Naive idea
     * n = 1, [0]
     * n = 2, [-1, 1]
     * <p>
     * Now write more based on this
     * n = 3, [-2, 0, 2]
     * n = 4, [-3, -1, 1, 3]
     * n = 5, [-4, -2, 0, 2, 4]
     * <p>
     * It spreads like the wave.
     * <p>
     * <p>
     * Explanation
     * Find the rule
     * A[i] = i * 2 - n + 1
     * <p>
     * Complexity
     * Time O(N)
     * Space O(N)
     */
    public int[] sumZero(int n) {
        int[] A = new int[n];
        for (int i = 0; i < n; ++i)
            A[i] = i * 2 - n + 1;
        return A;
    }
}
