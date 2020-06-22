package dynamic.programming.matrix.chain.multiplication.variants;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring
 * of the partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning
 * of “ababbbabbababa”. Determine the fewest cuts needed for palindrome partitioning of a given string.
 * For example, minimum 3 cuts are needed for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”.
 * If a string is palindrome, then minimum 0 cuts are needed. If a string of length n containing all
 * different characters, then minimum n-1 cuts are needed.
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println("Min cuts needed for Palindrome Partitioning using recursion is : " + new PalindromePartitioning().minimumPartitionsRequiredRecursive("ababbbabbababa"));
        System.out.println("Min cuts needed for Palindrome Partitioning using memoization is : " + new PalindromePartitioning().minimumPartitionsRequiredMemoization("ababbbabbababa"));
    }

    private int minimumPartitionsRequiredRecursive(String str) {
        return minimumPartitionsRequiredRecursive(str, 0, str.length() - 1);
    }

    private int minimumPartitionsRequiredRecursive(String str, int i, int j) {
        if (i >= j)
            return 0;
        if (isPalindrome(str, i, j))
            return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int tempResult = minimumPartitionsRequiredRecursive(str, i, k) +
                    minimumPartitionsRequiredRecursive(str, k + 1, j) +
                    1; // This "1" is the cost of making two partitions. First from (i to k) & second (k + 1 to j)
            min = Math.min(min, tempResult);
        }

        return min;
    }

    int[][] memo;

    private int minimumPartitionsRequiredMemoization(String str) {
        int n = str.length();
        initializeMemoTable(n);
        return minimumPartitionsRequiredMemoization(str, 0, n - 1);
    }

    private int minimumPartitionsRequiredMemoization(String str, int i, int j) {
        if (i >= j)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];

        if (isPalindrome(str, i, j))
            return 0;

        int min = Integer.MAX_VALUE;

        for (int k = i; k <= j - 1; k++) {
            int tempResult = minimumPartitionsRequiredMemoization(str, i, k) +
                    minimumPartitionsRequiredMemoization(str, k + 1, j) +
                    1;

            min = Math.min(min, tempResult);
        }

        return memo[i][j] = min;
    }

    private boolean isPalindrome(String str, int i, int j) {
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    private void initializeMemoTable(int n) {
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                memo[i][j] = -1;
    }
}
