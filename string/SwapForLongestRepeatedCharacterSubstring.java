package string;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
 * Example 2:
 * <p>
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
 * Example 3:
 * <p>
 * Input: text = "aaabbaaa"
 * Output: 4
 * Example 4:
 * <p>
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
 * Example 5:
 * <p>
 * Input: text = "abcdef"
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length <= 20000
 * text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedCharacterSubstring {

    class CharState {
        char character;
        int start;
        int end;

        public CharState(char ch, int s, int e) {
            character = ch;
            start = s;
            end = e;
        }
    }

    /**
     * The core algorithm is:
     * <p>
     * Identify the count for repeat characters
     * Let's say the example is aabaaba.
     * We should get some thing a-2, b-1, a-2, b-1, a-1.
     * <p>
     * Get the total count of one character.
     * take the same case for example, aabaaba, a total count = 5, b =2.
     * This will be used later.
     * <p>
     * The answer contains two scenarios.
     * // scenario 1, aaabba, find another a to replace b to increase maxlen+1; so result would be aaaabb.
     * // scenario 2, aabaaba, firstly find the middle char b, length equals to 1, then make sure the left side and right side character are the same, then find if there is another a to replace b, update maxLen accordingly.
     */
    public int maxRepOpt1(String text) {
        List<CharState> charStateList = new ArrayList<>();
        int startPos = 0;
        int endPos = 0;

        char[] arr = text.toCharArray();

        char prevChar = arr[0];

        int i = 1;

        int[] count = new int[26];

        count[prevChar - 'a']++;

        while (i < arr.length) {
            char currChar = arr[i];

            count[currChar - 'a']++;

            if (currChar != prevChar) {
                charStateList.add(new CharState(prevChar, startPos, endPos));
                prevChar = currChar;
                startPos = i;
            }
            endPos = i;

            i++;
        }

        charStateList.add(new CharState(prevChar, startPos, endPos));

        int maxLen = 1;
        // scenario 1, aaabba, find another a to replace b to increase maxlen + 1;
        for (CharState state : charStateList) {
            int len = state.end - state.start + 1;
            if (len < count[state.character - 'a']) {
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }

        // scenario 2, aaabaa, find another a to replace b
        CharState prevState = charStateList.get(0);
        i = 1;
        while (i < charStateList.size() - 1) {
            CharState currState = charStateList.get(i);
            CharState nextState = charStateList.get(i + 1);

            int prevLen = prevState.end - prevState.start + 1;
            int nextLen = nextState.end - nextState.start + 1;

            if (currState.start == currState.end && prevState.character == nextState.character) {
                int newTotalLen = prevLen + nextLen;
                if (newTotalLen < count[prevState.character - 'a']) {
                    newTotalLen++;
                }
                maxLen = Math.max(maxLen, newTotalLen);
            }

            prevState = currState;
            i++;
        }

        return maxLen;
    }
}
