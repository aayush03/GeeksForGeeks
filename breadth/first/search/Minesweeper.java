package breadth.first.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */

/**
 * https://leetcode.com/problems/minesweeper/
 */

/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 * <p>
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * <p>
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
 * <p>
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * <p>
 * Click : [3,0]
 * <p>
 * Output:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * Example 2:
 * <p>
 * Input:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * Click : [1,2]
 * <p>
 * Output:
 * <p>
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * <p>
 * <p>
 * Note:
 * <p>
 * The range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have been revealed).
 * For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 */
public class Minesweeper {

    public static final char UNREVEALED_MINE = 'M';
    public static final char UNREVEALED_EMPTY_SQUARE = 'E';
    public static final char REVEALED_BLANK_SQUARE = 'B';
    public static final char REVEALED_MINE = 'X';

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;

        int[] d_x = {-1, 1, 0, 0, 1, -1, 1, -1};
        int[] d_y = {0, 0, -1, 1, 1, -1, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1], count = 0;

            if (board[row][col] == UNREVEALED_MINE) {
                board[row][col] = REVEALED_MINE;
                return board;
            }

            for (int i = 0; i < 8; i++) {
                int r = row + d_x[i], c = col + d_y[i];
                if (isSafe(board, r, c, m, n, UNREVEALED_MINE)) {
                    count++;
                }
            }
            if (count > 0)
                board[row][col] = (char) (count + '0');
            else {
                board[row][col] = REVEALED_BLANK_SQUARE;
                for (int i = 0; i < 8; i++) {
                    int r = row + d_x[i], c = col + d_y[i];
                    if (isSafe(board, r, c, m, n, UNREVEALED_EMPTY_SQUARE)) {
                        queue.add(new int[]{r, c});
                        board[r][c] = REVEALED_BLANK_SQUARE;
                    }
                }
            }

        }
        return board;
    }

    private boolean isSafe(char[][] board, int r, int c, final int m, final int n, char boardValue) {
        return (r >= 0 && r < m) &&
                (c >= 0 && c < n) &&
                board[r][c] == boardValue;
    }
}
