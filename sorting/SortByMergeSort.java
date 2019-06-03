package sorting;

import java.util.Arrays;

public class SortByMergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 8, 1, 20, 30, 6, 2, 2, 9, 4, 10, 20};
        int len = arr.length;

        mergeSort(arr, 0, len - 1);
        System.out.println(Arrays.toString(arr));

    }

    protected static void mergeSort(int[] ar, int left, int right) {
        if (left == right)
            return;

        int mid = (left + right) / 2;

        mergeSort(ar, left, mid);
        mergeSort(ar, mid + 1, right);
        merge(ar, left, mid, right);
    }

    protected static void merge(int[] arr, int left, int mid, int right) {
        // Find sizes of two sub arrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        /* Create temp arrays */
        int LEFT_NEW[] = new int[n1];
        int RIGHT_NEW[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            LEFT_NEW[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            RIGHT_NEW[j] = arr[mid + 1 + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second sub arrays
        int i = 0, j = 0;

        // Initial index of merged sub array array
        int k = left;
        while (i < n1 && j < n2) {
            if (LEFT_NEW[i] <= RIGHT_NEW[j]) {
                arr[k] = LEFT_NEW[i];
                i++;
            } else {
                arr[k] = RIGHT_NEW[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of LEFT_NEW[] if any */
        while (i < n1) {
            arr[k] = LEFT_NEW[i];
            i++;
            k++;
        }

        /* Copy remaining elements of RIGHT_NEW[] if any */
        while (j < n2) {
            arr[k] = RIGHT_NEW[j];
            j++;
            k++;
        }
    }

}
