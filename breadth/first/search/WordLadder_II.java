package breadth.first.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Aayush Srivastava
 */
public class WordLadder_II {

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> nodeNeighbors = new HashMap<>();// Neighbors for every node
        Map<String, Integer> distance = new HashMap<>();// Distance of every node from the start node

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, nodeNeighbors, distance, new ArrayList<>(), res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<>());

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                List<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))// Found the shortest path
                            foundEnd = true;
                        else
                            queue.add(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    // Find all next level nodes.
    private List<String> getNeighbors(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char cur[] = node.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < cur.length; i++) {
                if (cur[i] == ch)
                    continue;
                char old_ch = cur[i];
                cur[i] = ch;
                if (dict.contains(String.valueOf(cur))) {
                    res.add(String.valueOf(cur));
                }
                cur[i] = old_ch;
            }

        }
        return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance, List<String> path, List<List<String>> res) {
        path.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<>(path));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, nodeNeighbors, distance, path, res);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}
