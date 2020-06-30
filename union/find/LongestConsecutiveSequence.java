package union.find;

/**
 * @author Aayush Srivastava
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Sample Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {

    }

    public int longestConsecutive(int[] nums) {
        int n = nums.length;

        if (n == 0)
            return 0;

        UnionFind uf = new UnionFind(nums);

        for (int num : nums) {
            if (uf.parent.containsKey(num + 1)) {
                uf.union(num, num + 1);
            }
        }

        int maxSize = Integer.MIN_VALUE;

        for (int size : uf.size.values()) {
            maxSize = Math.max(maxSize, size);
        }

        return maxSize;
    }

    private class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;

        public UnionFind(int[] nums) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (int i : nums) {
                parent.put(i, i);
                size.put(i, 1);
            }
        }

        private int find(int i) {
            while (i != parent.get(i)) {
                parent.put(i, parent.get(parent.get(i)));
                i = parent.get(i);
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
            if (size.get(rootP) > size.get(rootQ)) {
                parent.put(rootQ, rootP);
                int sizeOfP = size.get(rootP) + size.get(rootQ);
                size.put(rootP, sizeOfP);
            } else {
                parent.put(rootP, rootQ);
                int sizeOfQ = size.get(rootQ) + size.get(rootP);
                size.put(rootQ, sizeOfQ);
            }
        }
    }
}
