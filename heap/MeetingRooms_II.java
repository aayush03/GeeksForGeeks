package heap;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Sample Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 */
public class MeetingRooms_II {

    public static void main(String[] args) {
        System.out.println("Minimum number of meeting rooms required : " + new MeetingRooms_II().minMeetingRooms(new int[][]{
                {0, 30},
                {5, 10},
                {15, 20}
        }));
    }

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        if (n == 0)
            return 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//sort by start time

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//sorted by end time

        int count = 0;

        for (int i = 0; i < n; i++) {
            int[] arr = intervals[i];
            if (!pq.isEmpty()) {
                int[] brr = pq.peek();
                if (arr[0] >= brr[1]) { //equal check needed in case meeting end time is same as start time
                    pq.poll();
                } else {
                    count++;
                }
            } else {
                count++;
            }
            pq.offer(arr);
        }

        return count;
    }

}
