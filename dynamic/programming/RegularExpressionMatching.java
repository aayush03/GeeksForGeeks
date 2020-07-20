package dynamic.programming;

import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * ---->>>> '.' Matches any single character.
 * ---->>>> '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * 1.) s could be empty and contains only lowercase letters a-z.
 * 2.) p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * <p>
 * <p>
 * <p>
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * <p>
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;
        if (p.length() > 1 && p.charAt(1) == '*') {
            if(isMatch(s, p.substring(2))) //for handling when s = "aab" , p = "c*a*b" and output : true
                return true;
            if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) //for handling when s = "ab" , p = ".*" and output : true
                return isMatch(s.substring(1),p);
        } else {
            if (s.length() > 0 && p.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) //for handling when s = "abXXX" , p = "abXXX" and output : true
                return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }


    public boolean isMatchMemoized(String s, String p, Map<String, Boolean> dp) {
        String key = s + "-" + p;
        if (dp.containsKey(key))
            return dp.get(key);
        boolean ans = false;
        if (p.length() == 0)
            return s.length() == 0;
        if (p.length() > 1 && p.charAt(1) == '*') {
            ans = isMatchMemoized(s, p.substring(2), dp);
            if (ans)
                return true;
            if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
                ans = isMatchMemoized(s.substring(1), p, dp);
        } else {
            if (s.length() > 0 && p.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
                ans = isMatchMemoized(s.substring(1), p.substring(1), dp);
        }

        dp.put(key, ans);
        return ans;
    }

    /*public boolean isMatch(String s, String p) {
        return isMatch(s, p, '\0');
    }

    private boolean isMatch(String s, String p, char pred) {
        if (s.length() == 0) {
            if (p.length() == 0)
                return true;
            if (pred != '\0') {
                return isMatch(s, p.substring(1), '\0');
            } else if (p.length() > 1 && p.charAt(1) == '*') {
                return isMatch(s, p.substring(2), '\0');
            } else
                return false;
        } else if (p.length() == 0) {
            return false;
        } else if (pred != '\0') {
            if (s.charAt(0) == pred || pred == '.') {
                return isMatch(s.substring(1), p, pred) ||
                        isMatch(s, p.substring(1), '\0');
            } else {
                return isMatch(s, p.substring(1), '\0');
            }
        } else if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(1), p.charAt(0));
        } else if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') {
            return isMatch(s.substring(1), p.substring(1), '\0');
        } else
            return false;
    }

    public boolean isMatchMemoized(String s, String p) {
        return isMatchMemoized(s, p, '\0', new HashMap<>());
    }

    private boolean isMatchMemoized(String s, String p, char pred, Map<String, Boolean> dp) {
        String key = s + "-" + p;
        if (dp.containsKey(key))
            return dp.get(key);
        boolean ans = false;
        if (s.length() == 0) {
            if (p.length() == 0)
                return true;
            if (pred != '\0') {
                ans = isMatchMemoized(s, p.substring(1), '\0', dp);
            } else if (p.length() > 1 && p.charAt(1) == '*') {
                ans = isMatchMemoized(s, p.substring(2), '\0', dp);
            }
        } else if (p.length() == 0) {
            ans = false;
        } else if (pred != '\0') {
            if (s.charAt(0) == pred || pred == '.') {
                ans = isMatchMemoized(s.substring(1), p, pred, dp) ||
                        isMatchMemoized(s, p.substring(1), '\0', dp);
            } else {
                ans = isMatchMemoized(s, p.substring(1), '\0', dp);
            }
        } else if (p.length() > 1 && p.charAt(1) == '*') {
            ans = isMatchMemoized(s, p.substring(1), p.charAt(0), dp);
        } else if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') {
            ans = isMatchMemoized(s.substring(1), p.substring(1), '\0', dp);
        }

        return ans;
    }*/
}
