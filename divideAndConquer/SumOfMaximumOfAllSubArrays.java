package divideAndConquer;

/**
 * Given an array arr[] of length N, the task is to find the sum of the maximum elements of every possible sub-array of the array.
 *
 * arr[] = {1, 3, 1, 7}
 * Max of all sub-arrays:
 * {1} - 1
 * {1, 3} - 3
 * {1, 3, 1} - 3
 * {1, 3, 1, 7} - 7
 * {3} - 3
 * {3, 1} - 3
 * {3, 1, 7} - 7
 * {1} - 1
 * {1, 7} - 7
 * {7} - 7
 * 1 + 3 + 3 + 7 + 3 + 3 + 7 + 1 + 7 + 7 = 42
 */
public class SumOfMaximumOfAllSubArrays {

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 7};
        System.out.println(maxSumSubarray(arr, 0, arr.length - 1));

        int[] arr1 = new int[]{1, 1, 1, 1, 1};
        System.out.println(maxSumSubarray(arr1, 0, arr1.length - 1));
    }

    private static int maxSumSubarray(int[] arr, int left, int right) {
        if (left > right)
            return 0;
        int i = getIndexOfMaxElementInRange(arr, left, right);

        int leftSum = maxSumSubarray(arr, left, i - 1);
        int rightSum = maxSumSubarray(arr, i + 1, right);
        return arr[i] * (right - i + 1) * (i - left + 1)
                + leftSum
                + rightSum;
    }

    private static int getIndexOfMaxElementInRange(int[] arr, int left, int right) {
        int k = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] > arr[k])
                k = i;
        }

        return k;
    }
}
