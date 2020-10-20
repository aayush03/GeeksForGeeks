package string;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindrome_II {

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        char[] arr = s.toCharArray();
        while (left < right && arr[left] == arr[right]) {
            left++;
            right--;
        }
        return isPalindrome(arr, left + 1, right) || isPalindrome(arr, left, right - 1);
    }

    public boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left++] != arr[right--])
                return false;
        }
        return true;
    }
}
