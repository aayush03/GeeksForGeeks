package divideAndConquer;

public class FindClosestNumberInAnArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 6, 6, 8, 9};
        int targetNumber = 11;
        System.out.println(arr[modifiedBinarySearch(arr, 0, arr.length - 1, targetNumber, Integer.MAX_VALUE, 0)]);

        int[] arr1 = new int[]{2, 5, 6, 7, 8, 8, 9};
        targetNumber = 4;
        System.out.println(arr1[modifiedBinarySearch(arr1, 0, arr1.length - 1, targetNumber, Integer.MAX_VALUE, 0)]);
    }

    private static int modifiedBinarySearch(int[] arr, int left, int right, int x, int diff, int indexOfClosestElement) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x)
                return mid;

            int absDiff = Math.abs(arr[mid] - x);
            if (absDiff < diff) {
                diff = absDiff;
                indexOfClosestElement = mid;
            }

            if (arr[mid] < x)
                return modifiedBinarySearch(arr, mid + 1, right, x, diff, indexOfClosestElement);

            return modifiedBinarySearch(arr, left, mid - 1, x, diff, indexOfClosestElement);
        }

        return indexOfClosestElement;
    }
}
