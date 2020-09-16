package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author Aayush Srivastava
 */

/**
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
 *
 * Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseSchedule_II {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++)
            adjacencyList.add(new ArrayList<>());

        for (int[] preq : prerequisites)
            adjacencyList.get(preq[0]).add(preq[1]);

        boolean isGraphCyclic = isDirectedGraphCyclic(adjacencyList, numCourses);
        if (isGraphCyclic)
            return new int[]{};

        return topoSort(adjacencyList, numCourses);
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

    private int[] topoSort(List<List<Integer>> list, int N) {
        boolean[] visited = new boolean[N];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++)
            if (!visited[i])
                dfs(list, visited, i, stack);

        int[] topologicalSortedOrder = new int[N];
        int i = N - 1;
        while (!stack.isEmpty()) {
            topologicalSortedOrder[i--] = stack.pop();
        }

        return topologicalSortedOrder;
    }

    private static void dfs(List<List<Integer>> list, boolean[] visited, int sourceIndex, Stack<Integer> stack) {
        visited[sourceIndex] = true;


        Iterator itr = list.get(sourceIndex).listIterator();

        while (itr.hasNext()) {
            int currIndex = (int) itr.next();
            if (!visited[currIndex])
                dfs(list, visited, currIndex, stack);
        }

        stack.push(sourceIndex);
    }
}
