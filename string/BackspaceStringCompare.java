package string;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {
        if (S == null && T == null)
            return true;
        String s1 = S != null ? evaluateBackspaces(S) : "";
        String s2 = T != null ? evaluateBackspaces(T) : "";

        return s1.equals(s2);
    }

    private String evaluateBackspaces(String str) {
        StringBuilder sb = new StringBuilder();

        int n = str.length();

        char[] arr = str.toCharArray();

        int i = 0;
        int len = 0;
        while (i < n) {
            if (arr[i] == '#') {
                len = sb.length();
                if (len > 0) {
                    sb.setLength(len - 1);
                }
            } else {
                sb.append(arr[i]);
            }
            i++;
        }

        return sb.toString();
    }
}
