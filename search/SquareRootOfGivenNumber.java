package search;

/**
 * @author Aayush Srivastava
 */
public class SquareRootOfGivenNumber {

    public int mySqrt(int x) {
        if (x == 0)
            return x;
        int left = 1;
        int right = x;
        return binarySearch(left, right, x);
    }

    private int binarySearch(int left, int right, final int x) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= x / mid && (mid + 1) > x / (mid + 1))
                return mid;

            if (mid <= x / mid)
                return binarySearch(mid + 1, right, x);

            return binarySearch(left, mid, x);
        }

        return left;
    }
}
