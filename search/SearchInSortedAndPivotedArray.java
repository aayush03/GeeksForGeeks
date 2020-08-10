package search;

public class SearchInSortedAndPivotedArray {

    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int result = searchElement(arr, 9);

        if (result > -1) {
            System.out.println("Element found at index::" + result);
        } else {
            System.out.println("Element not found");
        }
    }

    private static int searchElement(int[] arr, int key) {
        int n = arr.length;
        int pivot = findPivot(arr, 0, n - 1);

        if (pivot == -1)
            return new BinarySearch().binarySearch(arr, 0, n - 1, key);
        if (arr[pivot] == key)
            return pivot;
        if (arr[0] <= key)
            return new BinarySearch().binarySearch(arr, 0, pivot - 1, key);

        return new BinarySearch().binarySearch(arr, pivot + 1, n - 1, key);

    }

    private static int findPivot(int arr[], int left, int right) {
        if (right < left)
            return -1;
        if (right == left)
            return left;

        int mid = left + (right - left) / 2;

        if (mid < right && arr[mid] > arr[mid + 1])
            return mid;

        if (mid > left && arr[mid] < arr[mid - 1])
            return (mid - 1);

        if (arr[left] >= arr[mid])
            return findPivot(arr, left, mid - 1);

        return findPivot(arr, mid + 1, right);
    }
}
