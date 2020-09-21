package breadth.first.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Sample Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"})));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int i = curr; i <= s.length(); i++) {
                if (visited.contains(i))
                    continue;
                if (dictionary.contains(s.substring(curr, i))) {
                    if (i == s.length())
                        return true;
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }
}
