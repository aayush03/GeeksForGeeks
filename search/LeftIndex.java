package search;

/**
 * Given a sorted array arr of N positive integers (elements may be repeated)
 * and a number x. The task is to find the leftmost index of x in the array arr.
 */
public class LeftIndex {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 2, 2, 3, 4, 5, 5, 6, 7};
        System.out.println(leftIndex(arr1.length, arr1, 1));
        int[] arr2 = new int[]{10, 20, 20, 20, 20, 20, 20};
        System.out.println(leftIndex(arr2.length, arr2, 20));

    }

    private static int leftIndex(int sizeOfArray, int arr[], int elementToSearch) {
        return findFirstIndex(arr, 0, sizeOfArray - 1, elementToSearch);
    }


    private static int findFirstIndex(int[] arr, int left, int right, int x) {
        if (right >= left) {

            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                if (mid == 0)
                    return mid;
                else if (arr[mid - 1] != x)
                    return mid;
                else
                    return findFirstIndex(arr, left, mid - 1, x);
            }

            if (arr[mid] > x)
                return findFirstIndex(arr, left, mid - 1, x);
            if (arr[mid] < x)
                return findFirstIndex(arr, mid + 1, right, x);
        }
        return -1;
    }
}
