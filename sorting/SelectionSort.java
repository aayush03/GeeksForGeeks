package sorting;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 1, 2};
        selectionSort(arr, arr.length);

        for (int i : arr)
            System.out.print(i + " ");
    }

    private static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int indexOfMinimumValue = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[indexOfMinimumValue]) // change '<' to '>' to sort in decreasing order
                    indexOfMinimumValue = j;

            int temp = arr[i];
            arr[i] = arr[indexOfMinimumValue];
            arr[indexOfMinimumValue] = temp;
        }
    }
}
