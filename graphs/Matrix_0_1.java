package graphs;

/**
 * @author Aayush Srivastava
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * <p>
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * Example 2:
 * <p>
 * Input:
 * [[0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * <p>
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [1,2,1]]
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class Matrix_0_1 {

    public int[][] updateMatrix(int[][] matrix) {
        Queue<Point> queue = new LinkedList<>();

        int m = matrix.length;
        int n = matrix[0].length;

        //Fill 1 with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = -1;
                } else {
                    queue.offer(new Point(i, j));
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // BFS starting from each 0 cell
        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            length++;
            for (int i = 0; i < size; i++) {
                Point curPoint = queue.poll();

                int x = curPoint.x;
                int y = curPoint.y;

                for (int[] dir : dirs) {
                    int x1 = x + dir[0];
                    int y1 = y + dir[1];
                    if (x1 >= 0 && y1 >= 0 && x1 < m && y1 < n && matrix[x1][y1] == -1) {
                        matrix[x1][y1] = length;
                        queue.offer(new Point(x1, y1));
                    }
                }
            }
        }

        return matrix;
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
