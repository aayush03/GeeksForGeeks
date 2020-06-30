package union.find;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;

/**
 * In a social group, there are N people, with unique integer ids from 0 to N-1.
 * <p>
 * We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.
 * <p>
 * Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.
 * <p>
 * Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.
 * <p>
 * Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.
 * <p>
 * Sample Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 */
public class EarliestMomentWhenEveryoneBecomeFriends {

    public static void main(String[] args) {

    }

    public int earliestAcq(int[][] logs, int N) {
        if (logs.length == 0)
            return -1;

        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        UnionFind uf = new UnionFind(N);

        for (int[] log : logs) {
            int friend1 = log[1];
            int friend2 = log[2];
            uf.union(friend1, friend2);
            if (uf.count == 1)
                return log[0];
        }

        return -1;
    }

    private class UnionFind {
        int[] parent;
        int[] size;
        int count;

        public UnionFind(int N) {
            count = N;
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
            count--;
        }
    }
}
