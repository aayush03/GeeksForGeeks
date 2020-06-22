package dynamic.programming.matrix.chain.multiplication.variants;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * Your goal is to know with certainty what the value of F is.
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 * <p>
 * Sample Input: K = 1, N = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 * If it didn't break, then we know with certainty F = 2.
 * Hence, we needed 2 moves in the worst case to know what F is with certainty.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 1 <= K <= 100
 * 2.) 1 <= N <= 10000
 */
public class EggDropping {

    public static void main(String[] args) {
        System.out.println("Minimum no of attempts to figure out the threshold floor for the egg to break using recursion : " + new EggDropping().minimumNumberOfAttemptsRecursive(2, 4));
        System.out.println("Minimum no of attempts to figure out the threshold floor for the egg to break using memoization : " + new EggDropping().minimumNumberOfAttemptsMemo(2, 4));
        System.out.println("Minimum no of attempts to figure out the threshold floor for the egg to break using recursion : " + new EggDropping().minimumNumberOfAttemptsRecursive(36, 2));
        System.out.println("Minimum no of attempts to figure out the threshold floor for the egg to break using memoization : " + new EggDropping().minimumNumberOfAttemptsMemo(36, 2));
        System.out.println();
        System.out.println("Minimum no of attempts to figure out the threshold floor for the egg to break using optimized  memoization : " + new EggDropping().minimumNumberOfAttemptsMemoizationOptimized(8, 10000));
    }

    private int minimumNumberOfAttemptsRecursive(int eggs, int floor) {
        if (floor == 0 || floor == 1)
            return floor;
        if (eggs == 0)
            return floor;

        int min = Integer.MAX_VALUE;

        for (int k = 1; k <= floor; k++) {
            // if egg breaks at k'th floor
            // At this k we tried so 1 attempt is consumed.
            // Now at this either a egg can break or can't break and since we want worst case so we choose Max or either 2
            int tempResult = 1 + Math.max(minimumNumberOfAttemptsRecursive(eggs - 1, k - 1),//+1 because we are attempting at k.
                    minimumNumberOfAttemptsRecursive(eggs, floor - k));

            min = Math.min(min, tempResult);
        }

        return min;
    }

    int[][] memo;

    private int minimumNumberOfAttemptsMemo(int eggs, int floors) {
        memo = new int[eggs + 1][floors + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return minimumNumberOfAttemptsMemoization(eggs, floors);
    }

    private int minimumNumberOfAttemptsMemoization(int eggs, int floors) {
        if (floors == 0 || floors == 1)
            return floors;
        if (eggs == 1)
            return floors;

        if (memo[eggs][floors] > -1)
            return memo[eggs][floors];

        int min = Integer.MAX_VALUE;

        for (int k = 1; k <= floors; k++) {
            /**
             *  if egg breaks at k'th floor
             * At this k we tried so 1 attempt is consumed.
             * Now at this either a egg can break or can't break and since we want worst case so we choose Max or either 2
             */
            int floorBelowK = 0;
            if (memo[eggs - 1][k - 1] > -1) {
                floorBelowK = memo[eggs - 1][k - 1];
            } else {
                floorBelowK = minimumNumberOfAttemptsMemoization(eggs - 1, k - 1);
                memo[eggs - 1][k - 1] = floorBelowK;
            }

            int floorAboveK = 0;
            if (memo[eggs][floors - k] > -1) {
                floorAboveK = memo[eggs][floors - k];
            } else {
                floorAboveK = minimumNumberOfAttemptsMemoization(eggs, floors - k);
                memo[eggs][floors - k] = floorAboveK;
            }

            // why +1 because we are attempting at k.
            int worstCase = 1 + Math.max(floorBelowK, floorAboveK);
            min = Math.min(min, worstCase);
        }

        return memo[eggs][floors] = min;
    }

    /**
     * Idea
     * We can use Binary Search here to get the minimum Math.max(left, right) + 1, when left and right are as close as possible.
     * <p>
     * Time complexity O(eggs * floors * log (floors))
     */
    public int minimumNumberOfAttemptsMemoizationOptimized(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        return helper(K, N, memo);
    }

    private int helper(int eggs, int floors, int[][] memo) {

        if (floors <= 1) return floors;
        if (eggs == 1) return floors;

        if (memo[eggs][floors] > 0) return memo[eggs][floors];

        int low = 1, high = floors, result = floors;

        while (low < high) {
            int mid = low + (high - low) / 2;

            int left = helper(eggs - 1, mid - 1, memo);
            int right = helper(eggs, floors - mid, memo);

            result = Math.min(result, Math.max(left, right) + 1);

            if (left == right) break; // Converge
            else if (left < right) low = mid + 1;
            else high = mid;
        }
        memo[eggs][floors] = result;
        return result;
    }
}
