package divideAndConquer;

public class MaximumSubArrayProduct {

    public static void main(String[] args) {

        int[] nums = new int[]{1, -2, -3, 0, 7, -8, -2};

        System.out.println("Maximum Sub Array Product is "
                + maxSubarrayProduct(nums));
    }

    private static int maxSubarrayProduct(int[] nums) {
        int size = nums.length;
        int max_so_far = 1, max_ending_here = 1, min_ending_here = 1;

        int flag = 0;

        for (int i = 0; i < size; i++) {
            if (nums[i] > 0) {
                max_ending_here = max_ending_here * nums[i];
                min_ending_here = Math.min(min_ending_here * nums[i], 1);
                flag = 1;
            } else if (nums[i] == 0) {
                max_ending_here = 1;
                min_ending_here = 1;
            } else {
                int temp = max_ending_here;
                max_ending_here = Math.max(min_ending_here * nums[i], 1);
                min_ending_here = temp * nums[i];
            }

            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;

        }
        if (flag == 0 && max_so_far == 1)
            return 0;
        return max_so_far;
    }
}
