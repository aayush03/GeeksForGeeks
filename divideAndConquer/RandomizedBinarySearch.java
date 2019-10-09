package divideAndConquer;

/**
 * Generate a random number t
 * Since range of number in which we want a random
 * number is [start, end]
 * Hence we do, t = t % (end-start+1)
 * Then, t = start + t;
 * Hence t is a random number between start and end
 */
public class RandomizedBinarySearch {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 10, 40};
        int n = arr.length;
        int key = 10;
        int result = randomizedBinarySearch(arr, 0, n - 1, key);
        System.out.println((result == -1) ? "Element is not present in array" :
                "Element is present at index " + result);
    }

    private static int getRandom(int x, int y) {
        return (x + (int) (Math.random() % (y - x + 1)));
    }

    private static int randomizedBinarySearch(int[] arr, int left, int right, int key) {
        if (right >= left) {
            int mid = getRandom(left, right);

            if (arr[mid] == key)
                return mid;

            if (arr[mid] > key)
                return randomizedBinarySearch(arr, left, mid - 1, key);

            return randomizedBinarySearch(arr, mid + 1, right, key);
        }

        return -1;
    }

}
