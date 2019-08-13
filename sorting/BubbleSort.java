package sorting;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 1, 2};
        bubbleSort(arr, arr.length);

        for (int i : arr)
            System.out.print(i + " ");
    }

    private static void bubbleSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) { // change '<' to '>' to sort in decreasing order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
