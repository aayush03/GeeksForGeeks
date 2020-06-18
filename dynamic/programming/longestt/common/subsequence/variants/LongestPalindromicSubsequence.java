package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Sample Input: "bbbab"
 * Output: 4
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println("Longest Palindromic Subsequence : " + new LongestPalindromicSubsequence().lps("agbcba"));
    }

    protected int lps(String s) {
        String y = reverse(s);
        return new LongestCommonSubsequence().lcsTabulation(s, y, s.length(), y.length());
    }

    private String reverse(String x) {
        if (null == x)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = x.length() - 1; i >= 0; i--) {
            sb.append(x.charAt(i));
        }

        return sb.toString();
    }
}
