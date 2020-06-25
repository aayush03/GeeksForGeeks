package sliding.window;

/**
 * @author Aayush Srivastava
 */

/**
 * You are given two strings s and t of the same length. You want to change s to t.
 * Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is,
 * the absolute difference between the ASCII values of the characters.
 * <p>
 * You are also given an integer maxCost.
 * Return the maximum length of a substring of s that can be changed to be the same as
 * the corresponding substring of twith a cost less than or equal to maxCost.
 * <p>
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 * <p>
 * Sample Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 */
public class LongestSubArrayWithSumLesThanOrEqualToGivenSum {

    public static void main(String[] args) {
        System.out.println("Get Equal Substrings Within Budget : " + new LongestSubArrayWithSumLesThanOrEqualToGivenSum().equalSubstring("abcd", "bcdf", 3));
        System.out.println("Get Equal Substrings Within Budget : " + new LongestSubArrayWithSumLesThanOrEqualToGivenSum().equalSubstring("abcd", "cdef", 3));
        System.out.println("Get Equal Substrings Within Budget : " + new LongestSubArrayWithSumLesThanOrEqualToGivenSum().equalSubstring("abcd", "acde", 0));
        System.out.println("Get Equal Substrings Within Budget : " + new LongestSubArrayWithSumLesThanOrEqualToGivenSum().equalSubstring("krrgw", "zjxss", 19));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int[] arr = new int[s.length()];
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        for (int i = 0; i < s.length(); i++)
            arr[i] = Math.abs(s1[i] - t1[i]);

        return lengthOfLongestSubArrayWithSumLesThanOrEqualToGivenSum(arr, maxCost);
    }

    private int lengthOfLongestSubArrayWithSumLesThanOrEqualToGivenSum(int[] arr, int targetSum) {
        int maxLen = 0;
        int left = 0;
        int currSum = 0;

        for (int right = 0; right < arr.length; right++) {
            currSum += arr[right];
            if (currSum <= targetSum)
                maxLen = Math.max(maxLen, right - left + 1);
            else
                currSum -= arr[left++];

        }

        return maxLen;
    }
}
