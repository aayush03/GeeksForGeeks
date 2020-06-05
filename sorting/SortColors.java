package sorting;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {

    public static void main(String[] args) {
        /*int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }*/


        String s = "50";

        String[] arr = s.split("");

        for (String k : arr)
            System.out.println(k);
    }

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0)
                swap(nums, i++, left++);
            else if (nums[i] == 2)
                swap(nums, i, right--);
            else
                i++;
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }
}
