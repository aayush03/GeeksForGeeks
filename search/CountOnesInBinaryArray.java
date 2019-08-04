package search;

public class CountOnesInBinaryArray {

    static int arr[] = new int[]{1, 1, 1, 1, 1, 0, 0, 0};

    public static void main(String[] args) {
        System.out.println(countOnes(arr.length));
    }

    private static int countOnes(int n) {
        return firstIndexOfZero(0, n);
    }

    private static int firstIndexOfZero(int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == 0 && arr[mid - 1] == 1)
                return mid;

            if (arr[mid - 1] == 0)
                return firstIndexOfZero(left, mid - 1);
            else if (arr[mid + 1] == 1)
                return firstIndexOfZero(mid + 1, right);

            return mid + 1;

        }
        return -1;
    }
}