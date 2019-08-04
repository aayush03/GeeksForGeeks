package search;

/**
 * You are given an array arr[] of N integers including 0.
 * The task is to find the smallest positive number missing from the array.
 */
public class SmallestPositiveMissingNumber {

    public static void main(String[] args) {
        int[] arr = new int[]{37, 6, -7, 41, -23, 15, 9, -14, -18, 1, -13, -22, 25, -43, 24};
        System.out.println(missingNumber(arr, arr.length));
    }

    private static int missingNumber(int arr[], int n) {
        int largest = arr[0];
        for (int i = 0; i < n; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }
        int[] existsArray = new int[largest + 1];
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0)
                existsArray[arr[i]]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if (existsArray[i] == 0)
                return i;
        }

        return n + 1;
    }
}
