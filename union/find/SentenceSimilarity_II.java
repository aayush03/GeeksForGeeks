package union.find;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aayush Srivastava
 */

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
 * determine if two sentences are similar.
 * <p>
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
 * if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation IS transitive. For example, if "great" and "good" are similar, and "fine" and "good" ARE SIMILAR,
 * then "great" and "fine" are similar.
 * <p>
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * <p>
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar,
 * even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * <p>
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class SentenceSimilarity_II {

    public static void main(String[] args) {

    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        int n = words1.length;
        int m = words2.length;

        if (n != m)
            return false;
        if (n == 0)
            return true;

        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        UnionFind uf = new UnionFind(pairs.size() * 2);
        for (List<String> list : pairs) {
            String u = list.get(0);
            String v = list.get(1);
            map.putIfAbsent(u, id++);
            map.putIfAbsent(v, id++);
            uf.union(map.get(u), map.get(v));
        }

        for (int i = 0; i < n; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            if (word1.equals(word2))
                continue;
            Integer w1 = map.get(word1);
            Integer w2 = map.get(word2);
            if (w1 == null || w2 == null ||
                    uf.find(w1) != uf.find(w2)) //if parents are not same then return false
                return false;
        }
        return true;
    }

    private class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }

            return i;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            // union by size
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }
    }
}
