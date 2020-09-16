package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class FindNumberOfDistinctIslands {

    int[] dx = new int[]{1, 0, -1, 0};
    int[] dy = new int[]{0, 1, 0, -1};

    public int numDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<String> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, grid, m, n, 0, 0, sb);
                    if (!set.contains(sb.toString())) {
                        set.add(sb.toString());
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, int[][] grid, final int ROW, final int COL, int xPos, int yPos, StringBuilder sb) {
        grid[x][y] = 0;
        sb.append(xPos + "" + yPos);

        for (int i = 0; i < 4; i++) {
            int x1 = dx[i] + x;
            int y1 = dy[i] + y;
            if (isSafe(grid, x1, y1, ROW, COL)) {
                dfs(x1, y1, grid, ROW, COL, xPos + dx[i], yPos + dy[i], sb);
            }
        }
    }

    private boolean isSafe(int[][] grid, int x, int y, final int ROW, final int COL) {
        return (x >= 0 && x < ROW) &&
                (y >= 0 && y < COL) &&
                (grid[x][y] == 1);
    }

}
