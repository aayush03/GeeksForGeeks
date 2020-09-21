package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */
public class CriticalConnectionsInA_Network {

    /**
     * First thought
     * Thinking for a little while, you will easily find out this theorem on a connected graph:
     *
     * An edge is a critical connection, if and only if it is not in a cycle.
     * So, if we know how to find cycles, and discard all edges in the cycles, then the remaining
     * connections are a complete collection of critical connections.
     *
     * How to find eges in cycles, and remove them
     * We will use DFS algorithm to find cycles and decide whether or not an edge is in a cycle.
     *
     * Define rank of a node: The depth of a node during a DFS. The starting node has a rank 0.
     *
     * Only the nodes on the current DFS path have non-special ranks. In other words, only the nodes that we've started visiting,
     * but haven't finished visiting, have ranks. So 0 <= rank < n.
     *
     * (For coding purpose, if a node is not visited yet, it has a special rank -2; if we've fully completed the visit of a node, it has a special rank n.)
     *
     * How can "rank" help us with removing cycles? Imagine you have a current path of length k during a DFS.
     * The nodes on the path has increasing ranks from 0 to kand incrementing by 1.
     * Surprisingly, your next visit finds a node that has a rank of p where 0 <= p < k.
     * Why does it happen? Aha! You found a node that is on the current search path! That means, congratulations, you found a cycle!
     *
     * But only the current level of search knows it finds a cycle. How does the upper level of search knows, if you backtrack?
     * Let's make use of the return value of DFS: dfs function returns the minimum rank it finds. During a step of search from node u to its neighbor v,
     * if dfs(v) returns something smaller than or equal to rank(u), then u knows its neighbor v helped it to find a cycle back to u or u's ancestor.
     * So u knows it should discard the edge (u, v) which is in a cycle.
     *
     * After doing dfs on all nodes, all edges in cycles are discarded. So the remaining edges are critical connections.
     *
     *
     *
     * Complexity analysis
     * DFS time complexity is O(|E| + |V|), attempting to visit each edge at most twice. (the second attempt will immediately return.)
     * As the graph is always a connected graph, |E| >= |V|.
     *
     * So, time complexity = O(|E|).
     *
     * Space complexity = O(graph) + O(rank) + O(connections) = 3 * O(|E| + |V|) = O(|E|).
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> connection : connections) {
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }

        Set<List<Integer>> connectionSet = new HashSet<>(connections);

        int[] rank = new int[n];
        Arrays.fill(rank, -2);

        dfs(graph, 0, 0, rank, connectionSet);

        return new ArrayList<>(connectionSet);
    }

    private int dfs(List<List<Integer>> graph, int node, int depth, int[] rank, Set<List<Integer>> connectionSet) {
        if (rank[node] >= 0) {// visiting (0<=rank<n), or visited (rank=n)
            return rank[node];// already visited node. return its rank
        }

        rank[node] = depth;

        int minDepthFound = depth;

        for (int neighbour : graph.get(node)) {
            if (rank[neighbour] == depth - 1) { // ignore parent || don't immediately go back to parent. that's why i didn't choose -1 as the special value, in case depth==0.
                continue;
            }

            int minDepth = dfs(graph, neighbour, depth + 1, rank, connectionSet);

            minDepthFound = Math.min(minDepthFound, minDepth);

            if (minDepth <= depth) {
                // to avoid the sorting just try to remove both combinations. of (x,y) and (y,x)
                connectionSet.remove(Arrays.asList(node, neighbour));
                connectionSet.remove(Arrays.asList(neighbour, node));
            }
        }

        return minDepthFound;
    }
}
