package graphs;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
public class ConnectingCitiesWithMinimumCost {

    /**
     * This solution is works using Prim's Algorithm for Minimum Spanning Tree.
     * A much more faster code is done using the Kruskal's Algorithm which is based on greedy approach and uses Union Find. (Check Union Find Code)
     */
    public int minimumCost(int N, int[][] connections) {
        List<List<int[]>> graph = generateGraph(N, connections);

        int[] val = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            val[i] = Integer.MAX_VALUE;
        }

        int numberOfVisitedNodes = 0;

        int totalCost = 0;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.add(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int city = curr[0];
            int cost = curr[1];

            if (visited[city])
                continue;

            visited[city] = true;
            totalCost += cost;
            numberOfVisitedNodes++;

            for (int[] arr : graph.get(city)) {
                int newCity = arr[0];
                int newCost = arr[1];

                while(!visited[newCity] && newCost<val[newCity])
                {
                    val[newCity]=newCost;
                    pq.add(new int[]{newCity, newCost});
                }
            }
        }

        return numberOfVisitedNodes == N ? totalCost : -1;
    }

    private List<List<int[]>> generateGraph(int N, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>(N + 1);

        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int[] conn : connections) {
            int c1 = conn[0];
            int c2 = conn[1];
            int cost = conn[2];

            graph.get(c1).add(new int[]{c2, cost});
            graph.get(c2).add(new int[]{c1, cost});
        }
        return graph;
    }
}
