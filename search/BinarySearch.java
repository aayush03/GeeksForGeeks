package search;

public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 4, 6, 8, 10, 15, 20, 44, 67};

        BinarySearch search = new BinarySearch();
        int result=search.binarySearch(arr, 0, arr.length-1, 44);

        if (result>-1) {
            System.out.println("Element found at index::"+result);
        } else {
            System.out.println("Element not found");
        }
    }

    public int binarySearch(int arr[], int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                return mid;
            }

            if (x < arr[mid])
                return binarySearch(arr, left, mid - 1, x);

            return binarySearch(arr, mid + 1, right, x);
        }
        return -1;
    }
}
