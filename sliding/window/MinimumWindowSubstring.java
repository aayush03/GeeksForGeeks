package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Sample Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * <p>
 * <p>
 * Note:
 * <p>
 * 1.) If there is no such window in S that covers all characters in T, return the empty string "".
 * 2.) If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null)
            return "";

        String res = "";
        int[] letterCountForStringT = new int[256];
        for (char c : t.toCharArray())
            letterCountForStringT[c]++;
        int left = 0, count = 0, minLen = Integer.MAX_VALUE;

        //count variable keeps track if all the characters from string T are present in the selected window
        for (int right = 0; right < s.length(); right++) {
            if (--letterCountForStringT[s.charAt(right)] >= 0)
                count++;
            while (count == t.length()) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }

                /**
                 * start reducing the window size from left till an element from T string is not encountered
                 * and the code will only satisfy the (--letterCountForStringT[s.charAt(right)] >= 0) condition
                 * when another instance of the removed character is encountered in the window and then only renter the while loop
                 */
                if (++letterCountForStringT[s.charAt(left)] > 0)
                    count--;
                left++;//reduce the window size from the left side
            }
        }

        return res;
    }
}
