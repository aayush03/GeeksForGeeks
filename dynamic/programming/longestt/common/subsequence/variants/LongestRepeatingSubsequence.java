package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string, print the longest repeating subsequence such that the two subsequence don’t have same string character
 * at same position, i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
 * <p>
 * Sample Input: str = "aabb"
 * Output: "ab"
 * <p>
 * Input: str = "aab"
 * Output: "a"
 * The two subsequence are 'a'(first) and 'a'
 * (second). Note that 'b' cannot be considered
 * as part of subsequence as it would be at same
 * index in both.
 */
public class LongestRepeatingSubsequence {

    public static void main(String[] args) {
        System.out.println("Length of Longest Repeating Subsequence : " + new LongestRepeatingSubsequence().lengthOfLongestRepeatingSubsequence("AABEBCDD"));
    }

    private int lengthOfLongestRepeatingSubsequence(String x) {
        String y = x;

        int m = x.length();

        int[][] t = new int[m + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                /**
                 * While taking LCS of the String with itself, only those characters should be considered part of the solution,
                 * for which the values are same ubt the index is different or else we will get the whole string as LCS
                 *
                 * This is why the condition for (i != j ) had to be used as well
                 */
                if (x.charAt(i - 1) == y.charAt(j - 1) && i != j)
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
            }
        }

        return t[m][m];
    }
}
