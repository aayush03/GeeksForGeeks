package sorting;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * Sample Input: [[0,30],[5,10],[15,20]]
 * Output: false
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        if (n == 0)
            return true;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            int[] arr = intervals[i];
            if (i < n - 1) {
                int[] brr = intervals[i + 1];
                if (arr[1] > brr[0])
                    return false;
            }
        }

        return true;
    }
}
