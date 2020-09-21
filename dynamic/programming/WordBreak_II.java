package dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreak_II {

    public static void main(String[] args) {
        System.out.println(new WordBreak_II().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(new WordBreak_II().wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
    }

    /**
     * used an array of Lists to maintain all of the valid start positions for every end position.
     * Then just do classic backtracking to find all solutions.
     * The time complexity is O(n*m) + O(n * number of solutions),
     * where n is the length of the input string, m is the length of the longest word in the dictionary.
     * The run time was 6ms. It is very efficient because DP is used to find out all the valid answers,
     * and no time is wasted on doing the backtracking.
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<Integer>[] starts = new List[s.length() + 1]; // valid start positions
        starts[0] = new ArrayList<>();

        Set<String> dict = new HashSet<>(wordDict);
        int maxLen = getMaxLen(dict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= i - maxLen && j >= 0; j--) {
                if (starts[j] == null)
                    continue;
                String word = s.substring(j, i);
                if (dict.contains(word)) {
                    if (starts[i] == null) {
                        starts[i] = new ArrayList<>();
                    }
                    starts[i].add(j);
                }
            }
        }

        List<String> result = new ArrayList<>();
        if (starts[s.length()] == null) {
            return result;
        }

        dfs(result, "", s, starts, s.length());
        return result;
    }

    private void dfs(List<String> result, String path, String s, List<Integer>[] starts, int end) {
        if (end == 0) {
            result.add(path.substring(1));
            return;
        }

        for (Integer start : starts[end]) {
            String word = s.substring(start, end);
            dfs(result, " " + word + path, s, starts, start);
        }
    }

    private int getMaxLen(Set<String> wordDict) {
        int max = 0;
        for (String s : wordDict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
