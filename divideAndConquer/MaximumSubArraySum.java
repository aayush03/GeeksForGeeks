package divideAndConquer;

public class MaximumSubArraySum {

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 5, 7};
        int n = arr.length;

        int[] nums = new int[]{-2, 1};
        int[] nums1 = new int[]{-2, -1};
        /*System.out.println("Maximum contiguous sum is " +
                maxSubArraySum(arr, 0, n - 1));*/

        System.out.println("Maximum contiguous sum is " + maxSubArray(nums) + " true / false ? " + (maxSubArray(nums) == 1));
        System.out.println("Maximum contiguous sum is " + maxSubArray(nums1) + " true / false ? " + (maxSubArray(nums1) == -1));
    }

    private static int maxSubArray(int[] nums) {
        int size = nums.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + nums[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

    private static int maxSubArraySum(int[] arr, int left, int right) {
        if (left == right)
            return arr[left];

        int mid = (left + right) / 2;

        return Math.max(
                Math.max(
                        maxSubArraySum(arr, left, mid),
                        maxSubArraySum(arr, mid + 1, right)
                ),
                maxCrossingSum(arr, left, mid, right)
        );
    }

    private static int maxCrossingSum(int[] arr, int left, int mid, int right) {
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        return leftSum + rightSum;
    }
}
