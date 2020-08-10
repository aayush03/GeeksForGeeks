package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */
public class RedundantConnection {

    /**
     * Refer optimized solution using Union Find
     */
    public static void main(String[] args) {

    }

    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(MAX_EDGE_VAL + 1);
        for (int i = 0; i <= MAX_EDGE_VAL; i++)
            graph.add(new ArrayList<>());

        Set<Integer> visited = new HashSet<>();
        for (int[] edge : edges) {
            visited.clear();
            int u = edge[0];
            int v = edge[1];
            if (!graph.get(u).isEmpty() && !graph.get(v).isEmpty() && dfs(graph, u, v, visited))
                return edge;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return new int[]{};
    }

    private boolean dfs(List<List<Integer>> graph, int source, int target, Set<Integer> visited) {
        if (!visited.contains(source)) {
            visited.add(source);
            if (source == target)
                return true;
            for (int neighbour : graph.get(source))
                if (dfs(graph, neighbour, target, visited))
                    return true;
        }
        return false;
    }
}
