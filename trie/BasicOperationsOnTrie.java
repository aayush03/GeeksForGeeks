package trie;

public class BasicOperationsOnTrie {

    private TrieNode root;

    public static void main(String[] args) {
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        BasicOperationsOnTrie trie = new BasicOperationsOnTrie();

        trie.root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length; i++)
            trie.insert(keys[i]);

        // Search for different keys
        if (trie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if (trie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if (trie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if (trie.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }

    private void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode trieNode = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (trieNode.children[index] == null)
                trieNode.children[index] = new TrieNode();

            trieNode = trieNode.children[index];
        }

        trieNode.isEndOfWord = true;
    }

    private boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode trieNode = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (trieNode.children[index] == null)
                return false;

            trieNode = trieNode.children[index];
        }

        return (trieNode != null && trieNode.isEndOfWord);
    }
}
