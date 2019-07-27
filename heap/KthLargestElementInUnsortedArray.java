package heap;

import java.util.PriorityQueue;

public class KthLargestElementInUnsortedArray {

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 5, 4, 2, 9};
        int k1 = 3;
        printKthLargestElement(arr1, arr1.length, k1);
        int[] arr2 = new int[]{4, 3, 7, 6, 5};
        int k2 = 5;
        printKthLargestElement(arr2, arr2.length, k2);
    }

    private static void printKthLargestElement(int[] arr, int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);
        for (int i = k; i < n; i++) {
            if (arr[i] > pq.peek()) {
                pq.poll();
                pq.add(arr[i]);

            }
        }
        System.out.println(pq.peek());

    }
}
