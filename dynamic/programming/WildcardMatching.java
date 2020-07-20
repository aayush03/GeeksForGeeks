package dynamic.programming;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * ---->>>> '?' Matches any single character.
 * ---->>>> '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) s could be empty and contains only lowercase letters a-z.
 * 2.) p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * <p>
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 * <p>
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 * <p>
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildcardMatching {

    public boolean isMatchGreedy(String s, String p) {
        int i = 0, j = 0, match = 0, star = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
                continue;
            }
            if (j < p.length() && p.charAt(j) == '*') {
                match = i;
                star = j++;
                continue;
            }
            if (star >= 0) {
                i = ++match;
                j = star + 1;
                continue;
            }
            return false;
        }
        while (j < p.length()) {
            if (p.charAt(j++) != '*') return false;
        }
        return true;
    }
}
