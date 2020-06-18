package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings str1 and str2, find the shortest string that has both str1 and str2 as subsequences.
 *
 * Sample Input: str1 = "AGGTAB", str2 = "GXTXAYB"
 * Output: "AGXGTXAYB"
 */
public class ShortestCommonSuperSequence {

    public static void main(String[] args) {
        System.out.println(" Length of Shortest Common Super Sequence : " + new ShortestCommonSuperSequence().lengthOfShortestCommonSuperSequence("AGGTAB", "GXTXAYB"));
    }

    private int lengthOfShortestCommonSuperSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int lcs = new LongestCommonSubsequence().lcsTabulation(str1, str2, m, n);

        return (m + n) - lcs;
    }
}
