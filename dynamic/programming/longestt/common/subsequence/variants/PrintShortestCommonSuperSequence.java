package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
 * <p>
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 * <p>
 * Sample Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) 1 <= str1.length, str2.length <= 1000
 * 2.) str1 and str2 consist of lowercase English letters.
 */
public class PrintShortestCommonSuperSequence {

    public static void main(String[] args) {
        System.out.println("Shortest Common Super Sequence : " + new PrintShortestCommonSuperSequence().printShortestCommonSuperSequence("abcdaf", "acbcf"));
    }

    private String printShortestCommonSuperSequence(String x, String y) {
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

        StringBuilder sb = new StringBuilder();

        int i = m;
        int j = n;

        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else {
                if (t[i - 1][j] > t[i][j - 1]) {
                    sb.append(x.charAt(i - 1));
                    i--;
                } else {
                    sb.append(y.charAt(j - 1));
                    j--;
                }

            }
        }

        while (i > 0)
            sb.append(x.charAt(--i));
        while (j > 0)
            sb.append(y.charAt(--j));

        return sb.reverse().toString();
    }
}
