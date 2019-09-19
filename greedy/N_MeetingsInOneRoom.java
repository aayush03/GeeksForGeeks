package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * There is one meeting room in a firm. There are N meetings in the form of (S[i], F[i])
 * where S[i] is start time of meeting i and F[i] is finish time of meeting i.
 *
 * What is the maximum number of meetings that can be accommodated in the meeting room?
 */

/**
 * Sample Input :
 * 2
 * 6
 * 1 3 0 5 8 5
 * 2 4 6 7 9 9
 * 8
 * 75250 50074 43659 8931 11273 27545 50879 77924
 * 112960 114515 81825 93424 54316 35533 73383 160252
 */
public class N_MeetingsInOneRoom {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine());

            int[] start_times = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            int[] finish_times = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Meetings[] arr = new Meetings[n];
            for (int i = 0; i < n; i++)
                arr[i] = new Meetings(i + 1, start_times[i], finish_times[i]);

            System.out.println(getMeetingsToBeExecuted(arr, n));
        }
    }

    private static String getMeetingsToBeExecuted(Meetings[] arr, int n) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0].position + " ");

        int i = 0;

        for (int j = 1; j < n; j++) {
            if (arr[j].start >= arr[i].finish) {
                i = j;
                sb.append(arr[j].position + " ");
            }
        }

        return sb.toString();
    }
}

class Meetings implements Comparable<Meetings> {
    int position;
    int start;
    int finish;


    public Meetings(int position, int start, int finish) {
        this.position = position;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public int compareTo(Meetings o) {
        if (this.finish >= o.finish)
            return 1;

        return -1;
    }
}