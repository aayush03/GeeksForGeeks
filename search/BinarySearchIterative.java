package search;

public class BinarySearchIterative {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 4, 6, 8, 10, 15, 20, 44, 67};

        BinarySearchIterative search = new BinarySearchIterative();
        int result = search.search(arr, arr.length - 1, 6);

        if (result > -1) {
            System.out.println("Element found at index::" + result);
        } else {
            System.out.println("Element not found");
        }
    }

    private int search(int[] ar, int n, int x) {
        int left = 0;
        int right = n;

        int i;

        int result = -1;

        for (i = left; i <= right; i++) {
            if (ar[i] == x)
                return i;
            int mid = left + (right - left) / 2;
            if (x < ar[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
