package sorting;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 1, 2};
        insertionSort(arr, arr.length);

        for (int i : arr)
            System.out.print(i + " ");
    }

    private static void insertionSort(int[] arr, int n) {
        int i, key, j;

        for (i = 1; i < n; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }

            arr[j + 1] = key;
        }
    }
}
