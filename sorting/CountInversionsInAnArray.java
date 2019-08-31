package sorting;

public class CountInversionsInAnArray {

    public static void main(String[] args) {
        long arr[] = new long[]{1, 20, 6, 4, 5};

        System.out.println(mergeSort(arr, arr.length));
    }

    private static long mergeSort(long arr[], long n) {
        // add your code here
        long[] temp = new long[(int) n];
        return mergeSort(arr, temp, 0, n - 1);
    }

    private static long mergeSort(long[] arr, long[] temp, long left, long right) {
        long mid, inv_count = 0;
        if (right > left) {
            mid = (right + left) / 2;

            inv_count = mergeSort(arr, temp, left, mid);
            inv_count += mergeSort(arr, temp, mid + 1, right);

            inv_count += merge(arr, temp, left, mid + 1, right);
        }
        return inv_count;
    }

    private static long merge(long[] arr, long[] temp, long left, long mid, long right) {
        long i, j, k;
        long inv_count = 0;

        i = left;
        j = mid;
        k = left;

        while ((i <= mid - 1) && (j <= right)) {
            if (arr[(int) i] <= arr[(int) j]) {
                temp[(int) k++] = arr[(int) i++];
            } else if (arr[(int) i] > arr[(int) j]) {
                temp[(int) k++] = arr[(int) j++];
                inv_count += mid - i;
            }
        }

        while (i <= mid - 1)
            temp[(int) k++] = arr[(int) i++];

        while (j <= right)
            temp[(int) k++] = arr[(int) j++];


        for (i = left; i <= right; i++)
            arr[(int) i] = temp[(int) i];

        return inv_count;
    }
}
