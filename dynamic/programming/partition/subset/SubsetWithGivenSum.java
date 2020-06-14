package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned has a subset which has sum equal to given sum
 */
public class SubsetWithGivenSum {

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 8, 10};
        System.out.println("Does array have subset with given sum ? : " + new SubsetWithGivenSum().hasSubsetWithGivenSum(arr, arr.length, 11));
        arr = new int[]{1, 2, 5};
        System.out.println("Does array have subset with given sum ? : " + new SubsetWithGivenSum().hasSubsetWithGivenSum(arr, arr.length, 4));
    }

    private boolean hasSubsetWithGivenSum(int[] arr, int n, int sum) {
        boolean[][] t = new boolean[n + 1][sum + 1];

        /**
         * Table initialization logic :
         *
         * First column always zero because empty subset always possible giving 0 sum for any length of array / subset.
         *
         * First row always false except first column (t[0][0]) because if we take any element then 0 sum is not possible.
         */
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0)
                    t[0][j] = false;
                if (j == 0)
                    t[i][0] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    t[i][j] = t[i - 1][j - arr[i - 1]] || t[i - 1][j];
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][sum];
    }
}
