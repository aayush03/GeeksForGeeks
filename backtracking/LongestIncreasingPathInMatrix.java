package backtracking;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInMatrix {

    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;

        int[][] cache = new int[m][n];
        int max = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int x, int y, final int m, final int n, int[][] cache) {
        if (cache[x][y] != 0)
            return cache[x][y];
        int max = 1;

        for (int i = 0; i < 4; i++) {
            int x_i = dx[i] + x;
            int y_i = dy[i] + y;
            if (isSafe(matrix, x, y, x_i, y_i, m, n)) {
                int len = 1 + dfs(matrix, x_i, y_i, m, n, cache);
                max = Math.max(len, max);
            }
        }

        return cache[x][y] = max;
    }

    private boolean isSafe(int[][] matrix, int x, int y, int x_i, int y_i, final int m, final int n) {
        return (x_i >= 0 && x_i < m) &&
                (y_i >= 0 && y_i < n) &&
                matrix[x][y] > matrix[x_i][y_i];
    }
}
