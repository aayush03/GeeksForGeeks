package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */
public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0, 1},
                {1, 0}
        };

        System.out.println(new ShortestPathInBinaryMatrix().shortestPathBinaryMatrix(grid));
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (n == 0 || grid[0][0] != 0)
            return -1;
        Pair src = new Pair(0, 0);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(src);

        int len = 1;

        int[] dx = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair cur = queue.poll();
                int x = cur.x;
                int y = cur.y;

                if (x == n - 1 && y == n - 1)
                    return len;

                for (int k = 0; k < 8; k++) {
                    int x1 = x + dx[k];
                    int y1 = y + dy[k];
                    if (isSafe(grid, x1, y1, n)) {
                        grid[x1][y1] = 1;
                        queue.add(new Pair(x1, y1));
                    }
                }
            }
            len++;
        }

        return -1;
    }

    private boolean isSafe(int[][] grid, int x, int y, final int n) {
        return (x >= 0 && x < n) &&
                (y >= 0 && y < n) &&
                (grid[x][y] == 0);
    }
}
