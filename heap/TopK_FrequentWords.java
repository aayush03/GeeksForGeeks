package heap;

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
 * Given a non-empty list of words, return the k most frequent elements.
 * <p>
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 * <p>
 * Sample Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * <p>
 * Sample Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * 1.) You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * 2.) Input words contain only lowercase letters.
 */
public class TopK_FrequentWords {

    public static void main(String[] args) {
        System.out.println(new TopK_FrequentWords().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 1));
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (null == words || words.length == 0)
            return res;
        Map<String, Integer> map = new HashMap<>();

        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        Queue<String> pq = new PriorityQueue<>(k, (a, b) -> {
            if (map.get(a) != map.get(b))
                return map.get(b) - map.get(a);
            return a.compareTo(b);
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry.getKey());
        }

        for (int i = 1; i <= k; i++)
            res.add(pq.poll());

        return res;
    }
}
