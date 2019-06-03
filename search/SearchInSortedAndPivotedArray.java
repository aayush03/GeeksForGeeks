package search;

public class SearchInSortedAndPivotedArray {

    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int result = searchElement(arr, 6);

        if (result > -1) {
            System.out.println("Element found at index::" + result);
        } else {
            System.out.println("Element not found");
        }
    }

    private static int searchElement(int[] ar, int x) {
        int[] left = new int[]{};
        int[] right = new int[]{};

        int pivot = 0;

        int size = ar.length;
        int mid = size/2;

        for (int i = 0; i < size; i++) {
            if (ar[mid] == x)
                return i;

            if (ar[mid] < ar[size-1])
                pivot = i - 1;
        }

        /**
         * change logic for finding out pivot as this implementation makes the worst case complexity as O(n)
         */
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == x)
                return i;

            if (ar[i] < ar[i + 1])
                pivot = i - 1;
        }

        for (int i = 0; i < pivot; i++)
            left[i] = ar[i];

        if (x > left[0])
            return new BinarySearch().binarySearch(left, 0, pivot, x);

        for (int i = pivot + 1, j = 0; i < ar.length; i++, j++)
            right[j] = ar[i];

        return new BinarySearch().binarySearch(right, 0, ar.length - pivot - 1, x);

    }

    int findPivot(int[] ar,int n, int left,int right) {

        return -1;
    }
}
