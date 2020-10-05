package union.find;

import java.util.Arrays;

/**
 * @author Aayush Srivastava
 */

/**
 * There are N cities numbered from 1 to N.
 * <p>
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 * <p>
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 */
public class ConnectingCitiesWithMinimumCostUsingUnionFind {

    public int minimumCost(int N, int[][] connections) {
        int res = 0;
        UnionFind uf = new UnionFind(N);
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        for (int[] conn : connections) {
            if (uf.union(conn[0] - 1, conn[1] - 1)) {
                res += conn[2];
            }
            if (uf.getCount() == 1)
                break;
        }
        return uf.getCount() == 1 ? res : -1;
    }

    private class UnionFind {

        int[] parent;
        int count;

        UnionFind(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++)
                parent[i] = i;
            count = N;
        }

        public boolean union(int v1, int v2) {
            int pv1 = find(v1);
            int pv2 = find(v2);
            if (pv1 == pv2)
                return false;
            parent[pv1] = pv2;
            count--;
            return true;
        }

        private int find(int v) {
            while (v != parent[v]) v = parent[v];
            return v;
        }

        public int getCount() {
            return count;
        }
    }
}
