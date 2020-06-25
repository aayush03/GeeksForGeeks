package sliding.window;

/**
 * @author Aayush Srivastava
 */
public class MaximumSumSubArrayOfSizeK {

    public static void main(String[] args) {
        System.out.println(findMaximumSumSubArrayOfSizeK(new int[]{100, 200, 300, 400}, 2));
        System.out.println(findMaximumSumSubArrayOfSizeK(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20}, 4));
    }

    private static int findMaximumSumSubArrayOfSizeK(int[] arr, int k) {
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < k; i++)
            currentSum += arr[i];
        maxSum = currentSum;
        for (int i = k; i < arr.length; i++) {
            currentSum -= arr[i - k];
            currentSum += arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
