package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Sample Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("aab"));
        System.out.println(new PalindromePartitioning().partition("aaabaa"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || "".equals(s)) {
            res.add(new ArrayList<>());
            return res;
        }

        dfs(s, 0, new ArrayList<>(), res);

        return res;
    }

    private void dfs(String s, int offSet, List<String> pal, List<List<String>> res) {
        if (offSet == s.length()) {
            res.add(new ArrayList<>(pal));
            return;
        }

        int currLength = pal.size();
        for (int i = offSet; i < s.length(); i++) {
            if (isPalindrome(s, offSet, i)) {
                pal.add(s.substring(offSet, i + 1));
                dfs(s, i + 1, pal, res);
                pal.remove(currLength);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }

        return true;
    }
}
