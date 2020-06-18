package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * <p>
 * Sample Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
 * Output : 5
 * The longest common substring is “Geeks” and is of length 5.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println("Length of longest common substring : " + new LongestCommonSubstring().lengthLongestCommonSubstring("GeeksforGeeks", "GeeksQuiz"));
        System.out.println("Length of longest common substring : " + new LongestCommonSubstring().lengthLongestCommonSubstring("abcde", "abfce"));
    }

    private int lengthLongestCommonSubstring(String x, String y) {
        int m = x.length();
        int n = y.length();

        int[][] t = new int[m + 1][n + 1];

        int max = Integer.MIN_VALUE; // to keep track of "longest" common substring
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                    max = Math.max(max, t[i][j]);
                } else
                    t[i][j] = 0; // 0 because only continuous characters need to be considered in case of substrings
            }
        }

        return max;
    }
}
