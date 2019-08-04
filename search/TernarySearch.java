package search;

public class TernarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 6, 8, 10, 15, 20, 44, 67};
        TernarySearch search = new TernarySearch();
        int result = search.ternarySearch(arr, 0, arr.length - 1, 44);

        if (result > -1) {
            System.out.println("Element found at index::" + result);
        } else {
            System.out.println("Element not found");
        }
    }

    private int ternarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            if (arr[mid1] == x)
                return mid1;
            else if (arr[mid2] == x)
                return mid2;

            if (arr[mid1] > x)
                return ternarySearch(arr, left, mid1 - 1, x);
            if (arr[mid2] < x)
                return ternarySearch(arr, mid2 + 1, right, x);

            return ternarySearch(arr, mid1 + 1, mid2 - 1, x);
        }
        return -1;
    }
}
