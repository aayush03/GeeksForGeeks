package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 *
 * Sample Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 */
public class PermutationInString {

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab","eidbaooo"));
        System.out.println(checkInclusion("ab","eidboaoo"));
    }

    public static boolean checkInclusion(String targetString, String sourceString) {
        int[] letterCountForTargetString = new int[256];

        for (char c : targetString.toCharArray())
            letterCountForTargetString[c]++;

        int left = 0, count = 0;

        for (int right = 0; right < sourceString.length(); right++) {
            if (--letterCountForTargetString[sourceString.charAt(right)] >= 0)
                count++;
            while (count == targetString.length()) {
                if (right - left + 1 == targetString.length())
                    return true;
                if (++letterCountForTargetString[sourceString.charAt(left)] > 0)
                    count--;
                left++;
            }
        }

        return false;
    }
}
