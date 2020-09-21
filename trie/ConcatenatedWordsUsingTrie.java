package trie;

import java.util.ArrayList;
import java.util.List;

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
public class ConcatenatedWordsUsingTrie {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (isConcatenated(word, 0, 0, root))
                result.add(word);
        }
        return result;
    }

    private boolean isConcatenated(String word, int index, int countConcatenatedWords, TrieNode root) {
        if (index == word.length())
            return countConcatenatedWords >= 2;
        TrieNode parent = root;
        for (int i = index; i < word.length(); i++) {
            if (parent.children[word.charAt(i) - 'a'] == null)
                return false;

            parent = parent.children[word.charAt(i) - 'a'];

            if (parent.isWord)
                if (isConcatenated(word, i + 1, countConcatenatedWords + 1, root))
                    return true;
        }
        return false;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode parent = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (parent.children[i] == null)
                    parent.children[i] = new TrieNode();
                parent = parent.children[i];
            }
            parent.isWord = true;
        }

        return root;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
        }
    }
}
