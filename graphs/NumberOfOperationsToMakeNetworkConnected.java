package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * <p>
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 * <p>
 * Example 1:
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 * <p>
 * Example 2:
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 * Example 3:
 * <p>
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * Output: -1
 * Explanation: There are not enough cables.
 * Example 4:
 * <p>
 * Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * No two computers are connected by more than one cable.
 */
public class NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1; // To connect all nodes need at least n-1 edges
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] c : connections) {
            graph[c[0]].add(c[1]);
            graph[c[1]].add(c[0]);
        }

        int components = 0;
        boolean[] visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            components += dfs(v, graph, visited);
        }

        return components - 1; // Need (components-1) cables to connect components together
    }

    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u])
            return 0;
        visited[u] = true;
        for (int v : graph[u])
            dfs(v, graph, visited);
        return 1;
    }
}
