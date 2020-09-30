package breadth.first.search;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * <p>
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
 * Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 * <p>
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5.
 * 0 <= routes[i][j] < 10 ^ 6.
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T)
            return 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                graph.computeIfAbsent(routes[i][j], k -> new ArrayList<>()).add(i);
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(S);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                List<Integer> buses = graph.get(cur);

                for (int bus : buses) {
                    if (visited.contains(bus))
                        continue;
                    visited.add(bus);
                    for (int busStop : routes[bus]) {
                        if (busStop == T)
                            return level + 1;
                        queue.add(busStop);
                    }
                }
            }
            level++;
        }

        return -1;
    }
}
