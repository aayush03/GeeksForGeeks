package dynamic.programming.partition.subset;

/**
 * @author Aayush Srivastava
 */
public class CountOfSubsetsWithGivenSum {

    public static void main(String[] args) {
        System.out.println("No of subsets with given sum : " + new CountOfSubsetsWithGivenSum().countSubsetsWithGivenSum(new int[]{2, 3, 5, 6, 8, 10, 3}, 10));
        System.out.println("No of subsets with given sum : " + new CountOfSubsetsWithGivenSum().countSubsetsWithGivenSum(new int[]{1, 5, 11, 5}, 11));
    }

    private int countSubsetsWithGivenSum(int[] arr, int sum) {
        int n = arr.length;

        int[][] t = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (i == 0)
                    t[0][j] = 0;
                if (j == 0)
                    t[i][0] = 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    t[i][j] = t[i - 1][j] + t[i - 1][j - arr[i - 1]];
                else
                    t[i][j] = t[i - 1][j];
            }
        }

        return t[n][sum];
    }
}
