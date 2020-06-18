package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * Sample Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 */
public class MinDeletionsToMakeStringPalindrome {

    public static void main(String[] args) {
        System.out.println("Minimum no of Insertions 'OR' Deletions to make string palindrome : " + new MinDeletionsToMakeStringPalindrome().minDeletions("zzazzz"));
    }

    /**
     * Same solution for minimum deletions / insertions to make string palindromic
     */
    private int minDeletions(String x) {
        int lps = new LongestPalindromicSubsequence().lps(x);

        return x.length() - lps;
    }
}
