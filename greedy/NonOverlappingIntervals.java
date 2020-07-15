package greedy;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        System.out.println("No of intervals to be removed to make all intervals non-overlapping : "+ new NonOverlappingIntervals().eraseOverlapIntervals(new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        }));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int pos = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (pos > intervals[i][0]) {
                count++;
                continue;
            }
            pos = intervals[i][1];
        }
        return count;
    }
}
