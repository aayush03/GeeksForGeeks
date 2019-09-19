package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given a set of N jobs where each job i has a deadline and profit associated to it.
 * Each job takes 1 unit of time to complete and only one job can be scheduled at a time.
 * We earn the profit if and only if the job is completed by its deadline. T
 * he task is to find the maximum profit and the number of jobs done.
 */

/**
 *  Sample Input :
 * 2
 * 4
 * 1 4 20 2 1 10 3 1 40 4 1 30
 * 5
 * 1 2 100 2 1 19 3 2 27 4 1 25 5 1 15
 */
public class JobSequencingProblem {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine());

            int[] arr1 = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            Job[] arr = new Job[n];

            int p = 0;
            for (int i = 0; i < n; i++) {
                int job_id = arr1[p++];
                int deadline = arr1[p++];
                int profit = arr1[p++];
                arr[i] = new Job(job_id, deadline, profit);
            }

            printNumberOfJobsDoneWithMaxProfit(arr, n);
        }
    }

    private static void printNumberOfJobsDoneWithMaxProfit(Job[] arr, int n) {
        Arrays.sort(arr);
        int count;

        int[] slot = new int[n];
        Arrays.fill(slot, -1);

        int totalProfit = 0;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, arr[i].deadline) - 1; j >= 0; j--) {
                if (slot[j] == -1) {
                    slot[j] = arr[i].job_id;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
        }
        count = (int) Arrays.stream(slot).filter(value -> value != -1).count();
        System.out.println(count + " " + totalProfit);
    }
}

class Job implements Comparable<Job> {
    int job_id;
    int deadline;
    int profit;

    public Job(int job_id, int deadline, int profit) {
        this.job_id = job_id;
        this.deadline = deadline;
        this.profit = profit;
    }

    @Override
    public int compareTo(Job o) {
        if (this.profit < o.profit)
            return 1;

        return -1;
    }
}