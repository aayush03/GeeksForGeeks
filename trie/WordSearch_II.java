package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearch_II {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode parent, List<String> res) {
        char c = board[i][j];

        if (c == '#' || parent.children[c - 'a'] == null)
            return;

        parent = parent.children[c - 'a'];

        if (parent.word != null) { //condition true in case a word is found
            res.add(parent.word);
            parent.word = null; //to avoid duplication of the same word without using a set
        }

        board[i][j] = '#'; //mark cell as visited

        if (i > 0)
            dfs(board, i - 1, j, parent, res);
        if (i < board.length - 1)
            dfs(board, i + 1, j, parent, res);
        if (j > 0)
            dfs(board, i, j - 1, parent, res);
        if (j < board[0].length - 1)
            dfs(board, i, j + 1, parent, res);

        board[i][j] = c; //reset cell to original value after backtracking

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
            parent.word = word;
        }

        return root;
    }

    class TrieNode {

        TrieNode[] children = new TrieNode[26];
        String word;
    }
}
