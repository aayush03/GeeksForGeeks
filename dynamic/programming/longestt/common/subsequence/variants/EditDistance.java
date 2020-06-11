package dynamic.programming.longestt.common.subsequence.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Sample Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(new EditDistance().editDistanceRecursive("horse", "ros"));
        System.out.println(new EditDistance().editDistanceDP("horse", "ros"));
        System.out.println(new EditDistance().editDistanceRecursive("intention", "execution"));
        System.out.println(new EditDistance().editDistanceDP("intention", "execution"));
    }

    private int editDistanceRecursive(String word1, String word2) {
        return editDistanceRecursive(word1, word2, word1.length(), word2.length());
    }

    private int editDistanceRecursive(String word1, String word2, int i, int j) {
        if (i == 0)
            return j;
        if (j == 0)
            return i;
        if (word1.charAt(i - 1) == word2.charAt(j - 1))
            return editDistanceRecursive(word1, word2, i - 1, j - 1);

        return 1 + min(
                editDistanceRecursive(word1, word2, i, j - 1), //Insert new character to word1
                editDistanceRecursive(word1, word2, i - 1, j), //Delete character from word1
                editDistanceRecursive(word1, word2, i - 1, j - 1) //Replace character from word2 in word1
        );
    }

    private int editDistanceDP(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;

        for (int j = 0; j <= n; j++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
            }
        }

        return dp[m][n];
    }

    private int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}
