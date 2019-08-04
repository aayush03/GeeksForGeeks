package queue;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumOfAllSubArraysOfSizeK {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6};

        printMaxOfSubArraysOfSizeK(arr, arr.length, 3);
        System.out.println();
    }

    private static void printMaxOfSubArraysOfSizeK(int[] arr, int n, int k) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            //remove all the elements which are smaller than the current elements
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i])
                deque.removeLast();
            //add new element at the end
            deque.addLast(i);
        }

        String s = "";
        for (int i = k; i < n; i++) {

            //before we do anything, print the first element in deque
            //since its a maximum among previous k elements
            s += arr[deque.peekFirst()]+" ";

            //Now remove the elements which are out for next window (next k elements)
            while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                deque.removeFirst();

            //Add the next element in the window = index i
            //remove all the elements which are smaller than the next element = index i
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i])
                deque.removeLast();
            //add new element at the end
            deque.addLast(i);
        }

        s += arr[deque.peekFirst()]+" ";
        System.out.print(s);
    }

}
