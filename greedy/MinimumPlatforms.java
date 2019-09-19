package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given arrival and departure times of all trains that reach a railway station.
 * Your task is to find the minimum number of platforms required for the railway station so that no train waits.
 *
 * Note: Consider that all the trains arrive on the same day and leave on the same day.
 * Also, arrival and departure times will not be same for a train, but we can have arrival time of one train equal to departure of the other.
 *
 * In such cases, we need different platforms, i.e at any given instance of time, same platform
 * can not be used for both departure of a train and arrival of another.
 */

/**
 * Sample Input :
 * 2
 * 6
 * 0900  0940 0950  1100 1500 1800
 * 0910 1200 1120 1130 1900 2000
 * 3
 * 0900 1100 1235
 * 1000 1200 1240
 */
public class MinimumPlatforms {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());

            int[] arrival_times = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] departure_times = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            System.out.println(getPlatFormReq(arrival_times, departure_times, n));
        }
    }

    private static int getPlatFormReq(int[] arrival_times, int[] departure_times, int n) {
        Arrays.sort(arrival_times);
        Arrays.sort(departure_times);

        int platformsNeeded = 1;
        int result = 0;

        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arrival_times[i] <= departure_times[j]) {
                platformsNeeded++;
                i++;

                if (platformsNeeded > result)
                    result = platformsNeeded;
            } else {
                platformsNeeded--;
                j++;
            }
        }

        return result;
    }

}