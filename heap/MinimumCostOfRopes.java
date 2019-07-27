package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCostOfRopes {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 6};
        printCost(arr);
    }

    private static void printCost(int[] arr) {
        Queue<Long> queue = new PriorityQueue<>();

        for (int i : arr)
            queue.add(Long.valueOf(i));
        long cost = 0;
        while (queue.size() >= 1) {
            long a = queue.poll();
            if (!queue.isEmpty()) {
                long b = queue.poll();
                long sum = a + b;
                cost += sum;
                queue.add(sum);
            }
        }

        System.out.println(cost);
    }
}
