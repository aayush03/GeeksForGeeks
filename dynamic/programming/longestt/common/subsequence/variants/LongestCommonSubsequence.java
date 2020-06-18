package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Longest Common Subsequence Problem solution using recursion
 * Given two sequences, find the length of longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * <p>
 * For example, “abc”,  “abg”, “bdf”, “aeg”,  ‘”acefg”, .. etc are subsequences of “abcdefg”.
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String x = "abcdgh";
        String y = "abedfhr";

        System.out.println("Longest Common Subsequence using recursion " + new LongestCommonSubsequence().lcs(x, y, x.length(), y.length()));
        System.out.println("Longest Common Subsequence using memoization " + new LongestCommonSubsequence().lcsMemoization(x, y, x.length(), y.length()));
        System.out.println("Longest Common Subsequence using tabulation " + new LongestCommonSubsequence().lcsTabulation(x, y, x.length(), y.length()));
    }

    private int lcs(String x, String y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        /**
         * If last character is same in both strings then we need to check for remainder lengths (m - 1) & (n - 1) respectively
         * in both strings and last character will be part of lcs so added "1" to the result
         */
        if (x.charAt(m - 1) == y.charAt(n - 1))
            return 1 + lcs(x, y, m - 1, n - 1);
        /**
         * If last character is not same then its possible that 2nd last character of "x" and last character of "y" might be same
         * OR
         * last character of "x" and 2nd last character of "y" might be same so we check both the possibilities and
         * take the max for "longest" common subsequence
         */
        return Math.max(lcs(x, y, m - 1, n), lcs(x, y, m, n - 1));
    }

    int[][] memo;

    private int lcsMemoization(String x, String y, int m, int n) {
        initializeMemoTable(m, n);
        return lcsMemo(x, y, m, n);
    }

    private int lcsMemo(String x, String y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;

        if (memo[m][n] != -1)
            return memo[m][n];

        if (x.charAt(m - 1) == y.charAt(n - 1))
            return memo[m][n] = 1 + lcsMemo(x, y, m - 1, n - 1);

        return memo[m][n] = Math.max(
                lcsMemo(x, y, m - 1, n),
                lcsMemo(x, y, m, n - 1)
        );

    }

    private void initializeMemoTable(int m, int n) {
        memo = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }
    }

    protected int lcsTabulation(String x, String y, int m, int n) {
        int[][] t = new int[m + 1][n + 1];

        //replaced "m" with "i" and "n" with "j" in iterative approach converted from recursion / memoization
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
            }
        }

        return t[m][n];
    }
}
