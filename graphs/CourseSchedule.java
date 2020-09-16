package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aayush Srivastava
 */

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
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
