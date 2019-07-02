package trie;

public class TrieNode {

    public static final int ALPHABET_SIZE = 26;

    TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    boolean isEndOfWord;

    public TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < ALPHABET_SIZE; i++)
            children[i] = null;
    }
}
