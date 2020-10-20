package dynamic.programming;

import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * <p>
 * You have to finish at least one task every day.
 * The difficulty of a job schedule is the sum of difficulties of each day of the d days.
 * The difficulty of a day is the maximum difficulty of a job done in that day.
 * <p>
 * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
 * <p>
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * <p>
 * Example 2:
 * <p>
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * <p>
 * Example 3:
 * <p>
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 * <p>
 * Example 4:
 * <p>
 * Input: jobDifficulty = [7,1,7,1,7,1], d = 3
 * Output: 15
 * <p>
 * Example 5:
 * <p>
 * Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * Output: 843
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinimumDifficultyOfJobSchedule {

    /**
     * dfs help find the the minimum difficulty
     * if start work at ith job with d days left.
     * <p>
     * If d = 1, only one day left, we have to do all jobs,
     * return the maximum difficulty of jobs.
     * <p>
     * Time complexity O(nnd)
     * Space complexity O(nd)
     */
    public int minDifficulty(int[] A, int D) {
        int n = A.length, inf = Integer.MAX_VALUE, maxd;
        if (n < D) return -1;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i)
            dp[i] = Math.max(dp[i + 1], A[i]);
        for (int d = 2; d <= D; ++d) {
            for (int i = 0; i <= n - d; ++i) {
                maxd = 0;
                dp[i] = inf;
                for (int j = i; j <= n - d; ++j) {
                    maxd = Math.max(maxd, A[j]);
                    dp[i] = Math.min(dp[i], maxd + dp[j + 1]);
                }
            }
        }
        return dp[0];
    }

    public int minDifficulty(List<Integer> complexity, int days) {
        if (complexity == null || complexity.size() < 1 || days < 1 || days > complexity.size()) {
            return -1;
        }
        int[] prevDP = new int[complexity.size()];
        int[] curDP = new int[complexity.size()];

        for (int j = 0; j < complexity.size(); j++) {
            prevDP[j] = (j < 1 ? complexity.get(0) : Math.max(prevDP[j - 1], complexity.get(j)));
        }
        for (int i = 1; i < days; i++) {
            for (int j = i; j < complexity.size(); j++) {
                int tmpDiffMax = 0, tmpAccMin = Integer.MAX_VALUE;
                for (int k = j; k >= i; k--) {
                    tmpDiffMax = Math.max(tmpDiffMax, complexity.get(k));
                    tmpAccMin = Math.min(tmpAccMin, tmpDiffMax + prevDP[k - 1]);
                }
                curDP[j] = tmpAccMin;
            }
            int[] tmpDP = prevDP;
            prevDP = curDP;
            curDP = tmpDP;
        }
        return prevDP[complexity.size() - 1];
    }

}
