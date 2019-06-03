package arrays.basics;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {
        RotateArray rotate = new RotateArray();
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        rotate.rotateArrayLeft(arr, 2, 7);

        System.out.println(Arrays.toString(arr));
    }

    private void rotateArrayLeft(int[] ar, int d, int n) {
        for (int i = 0; i < d; i++)
            rotateArrayLeftByOne(ar, n);
    }

    private void rotateArrayLeftByOne(int[] ar, int n) {
        int i, temp = ar[0];
        for (i = 0; i < n - 1; i++) {
            ar[i] = ar[i + 1];
        }

        ar[i] = temp;
    }
}
