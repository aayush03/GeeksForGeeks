package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */
public class PrintLongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(new PrintLongestCommonSubsequence().lcs("acbcf", "abcdaf"));
        System.out.println(new PrintLongestCommonSubsequence().lcs("ABCDGH", "AEDFHR"));
    }

    private String lcs(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] t = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
            }
        }
        int i = m, j = n;

        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else {
                if (t[i - 1][j] > t[i][j - 1])
                    i--;
                else
                    j--;
            }
        }

        return sb.reverse().toString();
    }
}
