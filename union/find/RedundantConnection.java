package union.find;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * <p>
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers,
 * return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 * <p>
 * <p>
 * Sample Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *  1
 * / \
 * 2 - 3
 * <p>
 * <p>
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnection {

    public static void main(String[] args) {

    }

    /**
     * We need to remove the edge that is already has a common parent for its vertices before their union
     */
    public int[] findRedundantConnection(int[][] edges) {
        Set<Integer> nodes = getDistinctNodesFromEdges(edges);
        UnionFind uf = new UnionFind(nodes.size() + 1);

        int[] redundantEdge = null;

        for (int[] edge : edges) {
            int p = edge[0];
            int q = edge[1];

            if (uf.connected(p, q))
                redundantEdge = edge;
            else
                uf.union(p, q);
        }

        return redundantEdge;
    }

    private Set<Integer> getDistinctNodesFromEdges(int[][] edges) {
        Set<Integer> nodes = new HashSet<>();
        for (int[] edge : edges) {
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }

        return nodes;
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
