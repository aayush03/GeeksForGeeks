package divideAndConquer;

public class MedianOfTwoSortedArraysOfDiffSize {

    public static void main(String[] args) {
        int[] a = new int[]{3, 5, 10, 11, 17};
        int[] b = new int[]{9, 13, 20, 21, 23, 27};

        System.out.println(getMedian(a, b));
    }

    private static double getMedian(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        int minIndex = 0;
        int maxIndex = n; //Assuming n <= m

        int i = 0;
        int j = 0;

        int median = 0;

        while (minIndex <= maxIndex) {
            i = (minIndex + maxIndex) / 2;
            j = ((n + m + 1) / 2) - i;

            if (i < n && j > 0 && a[i] < b[j - 1])
                minIndex = i + 1;
            else if (i > 0 && j < m && a[i - 1] > b[j])
                maxIndex = i - 1;
            else {
                if (i == 0)
                    median = b[j - 1];
                else if (j == 0)
                    median = a[i - 1];
                else
                    median = Math.max(a[i - 1], b[j - 1]);

                break;
            }
        }

        if ((n + m) % 2 == 0)
            return (double) median;
        if (i == n)
            return (median + b[j]) / 2.0;
        if (j == m)
            return (median + a[i]) / 2.0;

        return (median + Math.min(a[i], b[j])) / 2.0;

    }
}
