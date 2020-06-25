package sliding.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();

        String res = "";

        for(int start = 0, end = 0; end < s.length(); end++) {
            char curr = s.charAt(end);
            if(map.containsKey(curr)) {
                start = Math.max(map.get(curr) + 1, start);
            }
            if(res.length() < end - start + 1) {
                res = s.substring(start, end + 1);
            }
            map.put(curr, end);
        }

        return res.length();
    }
}
