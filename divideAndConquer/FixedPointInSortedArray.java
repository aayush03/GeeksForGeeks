package divideAndConquer;

/**
 * Fixed Point in an array is an index i such that arr[i] is equal to i. Note that integers in array can be negative.
 */
public class FixedPointInSortedArray {

    public static void main(String[] args) {
        int arr[] = {-10, -1, 0, 3, 10, 11, 30, 50, 100};
        int n = arr.length;

        System.out.println(modifiedBinarySearch(arr, 0, n));
    }

    private static int modifiedBinarySearch(int[] arr, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == mid)
                return mid;

            if (mid > arr[mid])
                return modifiedBinarySearch(arr, mid + 1, right);

            return modifiedBinarySearch(arr, left, mid - 1);
        }

        return -1;
    }
}
