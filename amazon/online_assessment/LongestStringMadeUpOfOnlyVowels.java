package amazon.online_assessment;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given with a string . Your task is to remove atmost two substrings of any length from the given string such that the remaining string contains vowels('a','e','i','o','u') only.
 * Your aim is the maximise the length of the remaining string. Output the length of remaining string after removal of atmost two substrings.
 * NOTE: The answer may be 0, i.e. removing the entire string.
 * <p>
 * Sample Input
 * 2
 * earthproblem
 * letsgosomewhere
 * Sample Output
 * 3
 * 2
 */
public class LongestStringMadeUpOfOnlyVowels {

    public static void main(String[] args) {
        LongestStringMadeUpOfOnlyVowels object = new LongestStringMadeUpOfOnlyVowels();
        String[] testcases = {"earthproblem", "letsgosomewhere", "aaayyyaayyayaaayayaaa"};
        for (String testcase : testcases)
            System.out.println(testcase + ": " + solution(testcase));
    }

    static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    /**
     * 3 cases:
     * 1- Vowel at the start or the end (vcvc or cvcv)
     * 2- Vowel at the start and the end (vcvcv)
     * 3- No Vowel at the start and the end (cvc)
     *
     * reduce case 1 and 2 to case 3 and get the longest vowel substring
     * the result is vowels at the start + vowels at the end + longest vowel substring size
     */
    static int solution(String S) {
        int left = 0, right = S.length() - 1, res = 0;
        while (left < right) {
            if (vowels.contains(S.charAt(left))) {
                left++;
                res++;
            } else if (vowels.contains(S.charAt(right))) {
                right--;
                res++;
            } else break;
        }

        res += longestVowelSubstring(S, left, right);

        return res;
    }

    private static int longestVowelSubstring(String S, int left, int right) {
        int res = 0, max = 0;
        for (int k = left + 1; k < right; k++) {
            if (vowels.contains(S.charAt(k))) {
                if (res == 0) res = 1;
                if (vowels.contains(S.charAt(k - 1))) {
                    res++;
                }
                max = Integer.max(max, res);
            } else {
                res = 0;
            }
        }
        return max;
    }
}
