package search;

public class FirstAndLastPositionOfElementInArray {

    int low = -1, high = -1;

    public int[] searchRange(int[] nums, int target) {
        low = first(nums, 0, nums.length - 1, target);
        high = last(nums, low, nums.length, target);
        return new int[]{low, high};
    }

    private int first(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                if (mid > 0 && arr[mid - 1] == x)
                    return first(arr, left, mid - 1, x);
                return mid;
            }
            if (arr[mid] > x)
                return first(arr, left, mid - 1, x);
            return first(arr, mid + 1, right, x);
        }
        return -1;
    }

    private int last(int[] arr, int left, int right, int x) {
        if (left != -1 && right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                if (mid < arr.length - 1 && arr[mid + 1] == x)
                    return last(arr, mid + 1, right, x);
                return mid;
            }

            if (arr[mid] > x)
                return last(arr, left, mid - 1, x);
            return last(arr, mid + 1, right, x);
        }
        return left;
    }

}
