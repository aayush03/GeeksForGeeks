package arrays.basics;

public class ReverseArray {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        int[] ar = reverseArray(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(ar[i] + ",");
        }
    }

    static int[] reverseArray(int[] arr) {

        int len = arr.length;

        for (int i = 0, j = len - 1; j > i; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
}
