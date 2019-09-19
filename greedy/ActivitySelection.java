package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given N activities with their start and finish times.
 * Select the maximum number of activities that can be performed by a single person,
 * assuming that a person can only work on a single activity at a time.
 *
 * Note : The start time and end time of two activities may coincide.
 */

/**
 * Sample Input :
 * 2
 * 6
 * 1 3 2 5 8 5
 * 2 4 6 7 9 9
 * 4
 * 1 3 2 5
 * 2 4 3 6
 */
public class ActivitySelection {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine());

            int[] start_times = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] finish_times = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Activity[] arr = new Activity[n];

            for (int i = 0; i < n; i++)
                arr[i] = new Activity(start_times[i], finish_times[i]);

            System.out.println(getNumberOfActivities(arr, n));
        }
    }

    private static int getNumberOfActivities(Activity[] arr, int n) {
        Arrays.sort(arr);
        int count = 1;
        int i = 0;
        int k = 0;
        for (int j = 1; j < n; j++) {
            if (arr[j].startTime >= arr[i].finishTime) {
                i = j;
                count++;
            } else {
                k++;
            }
        }

        System.out.println("k::"+k);
        return count;
    }
}

class Activity implements Comparable<Activity> {
    int startTime;
    int finishTime;

    public Activity(int startTime, int finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    @Override
    public int compareTo(Activity o) {
        if (this.finishTime > o.finishTime)
            return 1;

        return -1;
    }
}