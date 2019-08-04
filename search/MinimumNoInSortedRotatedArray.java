package search;

/**
 * Given an array A  which is sorted and contains N distinct elements.
 * Also, this array is rotated at some unknown point.
 * The task is to find the minimum element in it
 */
public class MinimumNoInSortedRotatedArray {

    public static void main(String[] args) {
        int[] arr = new int[]{10, 20, 30, 45, 50, 60, 4, 6};
        System.out.println(minNumber(arr, 0, arr.length - 1));
    }

    private static long minNumber(int arr[], long left, long right) {
        if (right > left) {
            long mid = left + (right - left) / 2;

            if (mid == 0 && arr[(int) mid] < arr[(int) mid + 1])
                return arr[(int) mid];
            if (mid != 0 && arr[(int) mid] < arr[(int) mid - 1])
                return arr[(int) mid];

            if (arr[(int) mid] < arr[(int) right])
                return minNumber(arr, left, mid - 1);

            return minNumber(arr, mid + 1, right);
        }
        return arr[(int) left];
    }

}
