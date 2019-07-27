package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class NearlySortedArray {

    public static void main(String[] args) {
        int[] arr1 = new int[]{6, 5, 3, 2, 8, 10, 9};
        int k1 = 3;
        printNearlySortedArray(arr1, arr1.length, k1);
        System.out.println();
        int[] arr2 = new int[]{4, 3, 1, 2, 5};
        int k2 = 2;
        printNearlySortedArray(arr2, arr2.length, k2);
    }

    private static void printNearlySortedArray(int[] arr, int n, int k) {
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        int[] res = new int[n];

        for (int i = 0; i <= k; i++)
            priorityQueue.add(arr[i]);

        int j = 0;
        for (int i = k + 1; i < n; i++, j++) {
            int root = priorityQueue.poll();
            res[j] = root;
            priorityQueue.add(arr[i]);
        }

        while (!priorityQueue.isEmpty()) {
            res[j++] = priorityQueue.poll();
        }

        for (int i : res)
            System.out.print(i + " ");
    }
}
