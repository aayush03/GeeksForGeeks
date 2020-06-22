package dynamic.programming.matrix.chain.multiplication.variants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * <p>
 * Sample Input: s1 = "great", s2 = "rgeat"
 * Output: true
 */
public class ScrambledString {

    public static void main(String[] args) {
        System.out.println("Are strings scrambled using recursion : " + new ScrambledString().isScrambledStringRecursive("great", "eatgr"));
        System.out.println("Are strings scrambled using memoization  : " + new ScrambledString().isScramble("great", "eatgr"));

        System.out.println("Are strings scrambled using recursion : " + new ScrambledString().isScrambledStringRecursive("we", "we"));
        System.out.println("Are strings scrambled using memoization  : " + new ScrambledString().isScramble("we", "we"));

        System.out.println("Are strings scrambled using recursion : " + new ScrambledString().isScrambledStringRecursive("phqtrnilf", "ilthnqrpf"));
        System.out.println("Are strings scrambled using memoization  : " + new ScrambledString().isScramble("phqtrnilf", "ilthnqrpf"));

        System.out.println("Are strings scrambled using recursion : " + new ScrambledString().isScrambledStringRecursive("abcde", "caebd"));
        System.out.println("Are strings scrambled using memoization  : " + new ScrambledString().isScramble("abcde", "caebd"));
    }

    private boolean isScrambledStringRecursive(String a, String b) {
        if (a.equals(b))
            return true;
        int n = a.length();
        if (n <= 1 || b.length() <= 1)
            return false;
        if (n != b.length())
            return false;

        boolean scrambleStrings = false;
        for (int i = 1; i < n; i++) {
            boolean isStringScrambledWithSwapOnLeftSide = isScrambledStringRecursive(a.substring(0, i), b.substring(n - i));
            boolean isStringScrambledWithSwapOnRightSide = isScrambledStringRecursive(a.substring(i, n), b.substring(0, n - i));

            boolean isStringScrambledWithoutSwapOnLeftSide = isScrambledStringRecursive(a.substring(0, i), b.substring(0, i));
            boolean isStringScrambledWithoutSwapOnRightSide = isScrambledStringRecursive(a.substring(i, n), b.substring(i, n));

            if (
                    (isStringScrambledWithSwapOnLeftSide && isStringScrambledWithSwapOnRightSide) ||
                            (isStringScrambledWithoutSwapOnLeftSide && isStringScrambledWithoutSwapOnRightSide)
            ) {
                scrambleStrings = true;
                break;
            }
        }

        return scrambleStrings;
    }

    Map<String, Boolean> memo;

    public boolean isScramble(String A, String B) {
        if (B.length() != A.length()) return false;
        if (A.compareTo(B) == 0)
            return true;
        if (A.length() == 0 || B.length() == 0)
            return false;

        memo = new HashMap<>();

        return isScrambledStringMemoization(A, B);
    }

    private boolean isScrambledStringMemoization(String a, String b) {
        String key = a + ":" + b;
        if (memo.containsKey(key))
            return memo.get(key);
        if (a.compareTo(b) == 0) {
            memo.put(key, true);
            return true;
        }
        int n = a.length();

        boolean scrambleStrings = false;
        for (int i = 1; i < n; i++) {
            if (
                    (isScrambledStringMemoization(a.substring(0, i), b.substring(n - i))
                            &&
                            isScrambledStringMemoization(a.substring(i, n), b.substring(0, n - i))
                    )
                            ||
                            (
                                    isScrambledStringMemoization(a.substring(0, i), b.substring(0, i))
                                            &&
                                            isScrambledStringMemoization(a.substring(i, n), b.substring(i, n))
                            )
            ) {
                scrambleStrings = true;
                break;
            }
        }
        memo.put(key, scrambleStrings);
        return scrambleStrings;
    }
}
