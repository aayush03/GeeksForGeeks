package search;

public class SquareRootUsingBinarySearch {

    public static void main(String[] args) {
        int n = 63;
        System.out.println("SquareRoot of " + n + " is " + squareRoot(n, 1, n / 2));
    }

    static int squareRoot(int n, int left, int right) {

        int mid = left + (right - left) / 2;

        if (mid * mid == n || (mid * mid < n && (mid + 1) * (mid + 1) > n))
            return mid;
        if (mid * mid < n)
            return squareRoot(n, mid + 1, right);
        else return squareRoot(n, left, mid - 1);

    }
}
