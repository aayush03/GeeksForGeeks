package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Sample Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */
public class MergeIntervals {

    public static void main(String[] args) {
        int[][] res = merge(new int[][]{new int[]{1, 4}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}, new int[]{3,7}});

        for (int[] arr : res) {
            System.out.print("[");
            for (int i : arr)
                System.out.print(i + " , ");
            System.out.print("]");
        }
    }

    public static int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<int[]> res = new ArrayList<>();

        int[] prev = intervals[0];

        for(int[] curr : intervals){
            if(prev[1] >= curr[0]){
                prev = new int[]{prev[0], Math.max(prev[1], curr[1])};
            }else{
                res.add(prev);
                prev = curr;
            }
        }
        res.add(prev);
        return res.toArray(new int[res.size()][]);
    }

    public static int[][] Merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1)
            return intervals;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort intervals, order by 0 index of each array
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]); // since array is sorted, we can add the 0 index of the sorted array

        for (int i = 1; i < n; i++) {
            int[] last = res.get(res.size() - 1); // get the last interval stored in the res
            int[] current = intervals[i];
            if (current[0] <= last[1]) {
                last[1] = Math.max(current[1], last[1]);
            } else {
                res.add(current);
            }
        }

        int[][] arr = new int[res.size()][2];
        int k = 0;
        for (int[] x : res) {
            arr[k++] = x;
        }

        return arr;
    }
}
