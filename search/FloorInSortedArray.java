package search;

/**
 * Given a sorted array arr[] of size N without duplicates, and given a value x.
 * Find the floor of x in given array. Floor of x is defined as the largest element K
 * in arr[] such that K is smaller than or equal to x
 */
public class FloorInSortedArray {

    public static void main(String[] args) {
        long[] arr = new long[]{1, 2, 8, 10, 11, 12, 19};
        System.out.println(findFloor(arr, 0, arr.length - 1, 5));
    }

    private static int findFloor(long arr[], int left, int right, long x) {
        return findFloor(arr, left, right, x, Integer.MIN_VALUE);
    }

    private static int findFloor(long arr[], int left, int right, long x, long largestSoFar) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] > x)
                return findFloor(arr, left, mid - 1, x, largestSoFar);

            if (arr[mid] <= x)
                return findFloor(arr, mid + 1, right, x, mid);
        }
        return largestSoFar != Integer.MIN_VALUE ? (int) largestSoFar : -1;
    }
}
