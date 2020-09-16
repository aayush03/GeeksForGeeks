package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 */
public class PrisonCellsAfter_N_Days {

    /**
     * The loop must start at day 1 since the edges cells[0] and cells[cell.length - 1] must be reset to both being 0.
     * So, they could start with: (0,0) which means the loop could start at day 0 but also note that the loop would continue on day 1.
     * Or they could start with: (0, 1), (1, 0), or (1,1) in which case they will be rest in day 1 to (0,0).
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] next = getNextPattern(cells);
        int[] day1 = next;
        List<int[]> ls = new ArrayList<>();
        ls.add(day1);
        for (int i = 1; i < N; i++) {
            next = getNextPattern(next);
            if (Arrays.equals(next, day1)) {
                int size = ls.size();
                int idx = (N % size) == 0 ? size - 1 : (N % size) - 1;
                return ls.get(idx);
            }
            ls.add(next);
        }
        return next;
    }

    private int[] getNextPattern(int[] cells) {
        int n = cells.length;
        int[] newCells = new int[n];
        for (int i = 1; i < n - 1; i++) {
            newCells[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return newCells;
    }
}
