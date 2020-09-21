package trie;

import java.util.ArrayList;
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
public class WordBreak_II_UsingTrie {

    Set<Character> dic = new HashSet<>();
    List<String> res = new ArrayList<>();
    TrieNode root;

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0)
            return res;
        root = buildTrie(wordDict);
        if (isValidString(s, dic)) {
            dfs(root, new ArrayList<>(), 0, s);
        }
        return res;
    }

    private void dfs(TrieNode node, List<String> temp, int start, String s) {
        if (start >= s.length()) {
            if (node == root)
                res.add(String.join(" ", temp));
            return;
        }
        char ch = s.charAt(start);
        TrieNode child = node.children[ch - 'a'];
        if (child == null) {
            return;
        }
        if (child.isWord) {
            //"cats, and, dog"
            temp.add(child.word);
            //root, "cats", 4->'a', "...."
            dfs(root, temp, start + 1, s);
            //"cats, and"
            temp.remove(temp.size() - 1);
        }
        //child, ""cats, and", 9
        dfs(child, temp, start + 1, s);
    }

    private boolean isValidString(String s, Set<Character> dic) {
        for (char c : s.toCharArray()) {
            if (!dic.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private TrieNode buildTrie(List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode parent = root;
            for (char c : word.toCharArray()) {
                dic.add(c);
                if (parent.children[c - 'a'] == null) {
                    parent.children[c - 'a'] = new TrieNode();
                }
                parent = parent.children[c - 'a'];
            }
            parent.isWord = true;
            parent.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isWord = false;
            this.word = null;
        }
    }
}