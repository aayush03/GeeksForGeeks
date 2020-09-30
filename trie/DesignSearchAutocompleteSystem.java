package trie;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
 * If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 *
 *  Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
 * The input is historical data. Sentences is a string array consists of previously typed sentences.
 * Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user.
 * The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system.
 * The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree.
 * Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
 * Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 *
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 *
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 *
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 *
 * Note:
 *
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 */
public class DesignSearchAutocompleteSystem {

    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> sentenceCountMap;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            sentenceCountMap = new HashMap<>();
        }
    }

    private TrieNode root;
    private StringBuilder prefix;

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    private void add(String word, int count) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.get(c) == null) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.sentenceCountMap.put(word, node.sentenceCountMap.getOrDefault(word, 0) + count);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix.toString(), 1);
            prefix = new StringBuilder();
            return new ArrayList<>();
        }

        prefix.append(c);
        TrieNode curr = root;
        for (char cc : prefix.toString().toCharArray()) {
            TrieNode next = curr.children.get(cc);
            if (next == null) {
                return new ArrayList<>();
            }
            curr = next;
        }

        Queue<Pair> pq = new PriorityQueue<>((a, b) -> (a.hotDegree == b.hotDegree ? a.sentence.compareTo(b.sentence) : b.hotDegree - a.hotDegree));
        for (String s : curr.sentenceCountMap.keySet()) {
            pq.add(new Pair(s, curr.sentenceCountMap.get(s)));
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().sentence);
        }
        return res;
    }

    class Pair {
        String sentence;
        int hotDegree;

        public Pair(String sentence, int hotDegree) {
            this.sentence = sentence;
            this.hotDegree = hotDegree;
        }
    }
}
