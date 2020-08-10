package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aayush Srivastava
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++)
            adjacencyList.add(new ArrayList<>());

        for (int[] preq : prerequisites)
            adjacencyList.get(preq[0]).add(preq[1]);
        boolean isGraphCyclic = isDirectedGraphCyclic(adjacencyList, numCourses);
        return isGraphCyclic ? false : true;
    }
    int WHITE = 0, GREY = 1, BLACK = 2;
    private boolean isDirectedGraphCyclic(List<List<Integer>> adjacencyList, int numCourses) {
        int[] color = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (color[i] == WHITE) {
                if (dfs(adjacencyList, color, i))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(List<List<Integer>> adjacencyList, int[] color, int u) {
        color[u] = GREY;

        for (int i : adjacencyList.get(u)) {
            if (color[i] == GREY)
                return true;

            if (color[i] == WHITE && dfs(adjacencyList, color, i))
                return true;
        }

        color[u] = BLACK;
        return false;
    }
}
