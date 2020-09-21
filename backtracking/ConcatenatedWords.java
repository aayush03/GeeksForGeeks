package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */


/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * <p>
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * <p>
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            min = Math.min(min, word.length());
        }
        for (String word : words) {
            if (isConcatenated(set, word, 0, min)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isConcatenated(Set<String> set, String word, int start, int min) {
        for (int i = start + min; i <= word.length() - min; i++) {
            if (set.contains(word.substring(start, i)) &&
                    (set.contains(word.substring(i)) || isConcatenated(set, word, i, min))) {
                return true;
            }
        }
        return false;
    }
}
