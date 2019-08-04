package search;

public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        subarraySum(arr.length, 15, arr);
    }

    private static void subarraySum(int n, int sum, int[] arr) {
        int curr_sum = arr[0], start = 0, i;

        for (i = 1; i <= n; i++) {
            while (curr_sum > sum && start < i - 1) {
                curr_sum = curr_sum - arr[start];
                start++;
            }

            if (curr_sum == sum) {
                int p = i - 1;
                System.out.println((start + 1) + " " + (p + 1));
                return;
            }

            if (i < n)
                curr_sum = curr_sum + arr[i];

        }

        System.out.println(-1);
    }
}
