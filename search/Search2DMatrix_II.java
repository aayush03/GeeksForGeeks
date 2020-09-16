package search;

/**
 * @author Aayush Srivastava
 */

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 */
public class Search2DMatrix_II {

    /**
     * Time Complexity = O(m + n)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;

        if (n == 0)
            return false;

        /**
         * Start from top right corner and adjust values of i & j accordingly
         */
        int i = 0;
        int j = n - 1;

        int val = matrix[i][j];

        while (i < m && j >= 0) {
            val = matrix[i][j];
            if (val == target)
                return true;
            if (target < val) {
                j--;
            } else if (target > val) {
                i++;
            }
        }

        return false;
    }
}
