package heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MedianInStreamOfNumbers {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int i = 0;
        while (n-- > 0) {
            arr[i++] = sc.nextInt();
        }*/

        int[] arr1 = new int[]{5, 15, 10, 20, 3};
        printMedianOfStream(arr1);
    }

    private static void printMedianOfStream(int[] arr) {
        double median = arr[0];

        Queue<Integer> smaller = new PriorityQueue<>
                (Collections.reverseOrder());

        Queue<Integer> greater = new PriorityQueue<>();

        smaller.add(arr[0]);
        System.out.println((int) median);

        for (int i = 1; i < arr.length; i++) {

            int x = arr[i];

            if (smaller.size() > greater.size()) {
                if (x < median) {
                    greater.add(smaller.remove());
                    smaller.add(x);
                } else
                    greater.add(x);
                median = (double) (smaller.peek() + greater.peek()) / 2;
            } else if (smaller.size() == greater.size()) {
                if (x < median) {
                    smaller.add(x);
                    median = (double) smaller.peek();
                } else {
                    greater.add(x);
                    median = (double) greater.peek();
                }
            } else {
                if (x > median) {
                    smaller.add(greater.remove());
                    greater.add(x);
                } else
                    smaller.add(x);
                median = (double) (smaller.peek() + greater.peek()) / 2;

            }
            System.out.println((int) median);
        }
    }
}
