package sorting;

import java.util.Arrays;

public class SortByQuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 8, 1, 20, 30, 6, 2, 2, 9, 4, 10, 20};
        int len = arr.length;

        quickSort(arr, 0, len - 1);
        System.out.println(Arrays.toString(arr));
    }

    protected static void quickSort(int[] ar, int left, int right) {
        if (left == right)
            return;
        if (left < right) {
            int p = partition(ar, left, right);
            quickSort(ar, left, p - 1);
            quickSort(ar, p + 1, right);
        }
    }

    protected static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = (left - 1); // index of smaller element
        for (int j = left; j < right; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[right] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }
}
