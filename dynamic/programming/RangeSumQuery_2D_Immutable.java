package dynamic.programming;

/**
 * @author Aayush Srivastava
 */
public class RangeSumQuery_2D_Immutable {

    /**
     * Construct a 2D array sums[row+1][col+1]
     *
     * (notice: we add additional blank row sums[0][col+1]={0} and blank column sums[row+1][0]={0} to remove the edge case checking), so, we can have the following definition
     *
     * sums[i+1][j+1] represents the sum of area from matrix[0][0] to matrix[i][j]
     *
     * To calculate sums, the ideas as below
     *
     * +-----+-+-------+     +--------+-----+     +-----+---------+     +-----+--------+
     * |     | |       |     |        |     |     |     |         |     |     |        |
     * |     | |       |     |        |     |     |     |         |     |     |        |
     * +-----+-+       |     +--------+     |     |     |         |     +-----+        |
     * |     | |       |  =  |              |  +  |     |         |  -  |              |
     * +-----+-+       |     |              |     +-----+         |     |              |
     * |               |     |              |     |               |     |              |
     * |               |     |              |     |               |     |              |
     * +---------------+     +--------------+     +---------------+     +--------------+
     *
     *    sums[i][j]      =    sums[i-1][j]    +     sums[i][j-1]    -   sums[i-1][j-1]   +
     *
     *                         matrix[i-1][j-1]
     * So, we use the same idea to find the specific area's sum.
     *
     * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
     * |               |   |         |    |   |   |           |   |         |    |   |   |          |
     * |   (r1,c1)     |   |         |    |   |   |           |   |         |    |   |   |          |
     * |   +------+    |   |         |    |   |   |           |   +---------+    |   +---+          |
     * |   |      |    | = |         |    | - |   |           | - |      (r1,c2) | + |   (r1,c1)    |
     * |   |      |    |   |         |    |   |   |           |   |              |   |              |
     * |   +------+    |   +---------+    |   +---+           |   |              |   |              |
     * |        (r2,c2)|   |       (r2,c2)|   |   (r2,c1)     |   |              |   |              |
     * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
     */
    private int[][] sum;

    public RangeSumQuery_2D_Immutable(int[][] matrix) {
        int row = matrix.length;
        int col = row > 0 ? matrix[0].length : 0;
        sum = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                sum[i][j] = matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
    }
}
