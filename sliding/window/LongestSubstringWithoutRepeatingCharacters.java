package sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Sample Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> characterPositionMap = new HashMap<>();

        String res = "";

        for (int start = 0, end = 0; end < s.length(); end++) {
            char curr = s.charAt(end);
            if (characterPositionMap.containsKey(curr)) {
                start = Math.max(characterPositionMap.get(curr) + 1, start);
            }
            if (res.length() < end - start + 1) {
                res = s.substring(start, end + 1);
            }
            characterPositionMap.put(curr, end);
        }

        return res.length();
    }
}
