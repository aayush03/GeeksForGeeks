package search;

/**
 * You are given an array of N+2 integer elements.
 * All elements of the array are in range 1 to N.
 * And all elements occur once except two numbers which occur twice.
 * Find the two repeating numbers.
 */
public class TwoRepeatedElements {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 3, 4, 3};
        twoRepeated(arr, 4);
    }

    private static void twoRepeated(int arr[], int n) {
        int[] indexArray = new int[n + 1];
        String res = "";
        for (int i = 0; i < n + 2; i++) {
            if (indexArray[arr[i]] != 0)
                res = res + arr[i] + " ";
            indexArray[arr[i]] = -1;
        }

        System.out.println(res);
    }
}
