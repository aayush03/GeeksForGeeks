package graphs;

/**
 * @author Aayush Srivastava
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
 * <p>
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th
 * employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 * <p>
 * The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform
 * his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
 * <p>
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes,
 * all his direct subordinates can start spreading the news).
 * <p>
 * Return the number of minutes needed to inform all the employees about the urgent news.
 * <p>
 * Sample Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 * <p>
 * <p>
 * Sample Input: n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
 * Output: 21
 * Explanation: The head has id = 6. He will inform employee with id = 5 in 1 minute.
 * The employee with id = 5 will inform the employee with id = 4 in 2 minutes.
 * The employee with id = 4 will inform the employee with id = 3 in 3 minutes.
 * The employee with id = 3 will inform the employee with id = 2 in 4 minutes.
 * The employee with id = 2 will inform the employee with id = 1 in 5 minutes.
 * The employee with id = 1 will inform the employee with id = 0 in 6 minutes.
 * Needed time = 1 + 2 + 3 + 4 + 5 + 6 = 21.
 * <p>
 * <p>
 * Sample Input: n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime = [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
 * Output: 3
 * Explanation: The first minute the head will inform employees 1 and 2.
 * The second minute they will inform employees 3, 4, 5 and 6.
 * The third minute they will inform the rest of employees.
 */
public class TimeNeededToInformAllEmployees {

    public static void main(String[] args) {
        TimeNeededToInformAllEmployees obj = new TimeNeededToInformAllEmployees();

        int[] manager = new int[]{1, 2, 3, 4, 5, 6, -1};
        int[] informTime = new int[]{0, 6, 5, 4, 3, 2, 1};
        int headID = 6;
        int n = manager.length;

        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutes(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesBFS(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesDFSWithMemoization(n, headID, manager, informTime));

        manager = new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        informTime = new int[]{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        headID = 0;
        n = manager.length;

        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutes(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesBFS(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesDFSWithMemoization(n, headID, manager, informTime));

        manager = new int[]{5, 9, 6, 10, -1, 8, 9, 1, 9, 3, 4};
        informTime = new int[]{0, 213, 0, 253, 686, 170, 975, 0, 261, 309, 337};
        headID = 4;
        n = manager.length;

        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutes(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesBFS(n, headID, manager, informTime));
        System.out.println("Time Needed to Inform All Employees : " + obj.numOfMinutesDFSWithMemoization(n, headID, manager, informTime));
    }

    public int numOfMinutesDFSWithMemoization(int n, int headID, int[] manager, int[] informTime) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfsWithMemoization(i, manager, informTime));
        }
        return res;
    }

    private int dfsWithMemoization(int i, int[] manager, int[] informTime) {
        if (manager[i] != -1) {
            informTime[i] += dfsWithMemoization(manager[i], manager, informTime);
            manager[i] = -1; //marking employee as visited
        }
        return informTime[i];
    }

    public int numOfMinutesBFS(int n, int headID, int[] manager, int[] informTime) {
        class Pair {
            int node;
            int time;

            Pair(int node, int time) {
                this.node = node;
                this.time = time;
            }
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int mgr = manager[i];
            graph.computeIfAbsent(mgr, k -> new ArrayList<>()).add(i);
        }

        int time = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(headID, 0));
        while (q.size() > 0) {
            Pair p = q.remove();
            time = Math.max(time, p.time);
            if (informTime[p.node] != 0) {
                List<Integer> children = graph.get(p.node);
                for (int i = 0; i < children.size(); i++)
                    q.add(new Pair(children.get(i), p.time + informTime[p.node]));
            }
        }
        return time;
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int mgr = manager[i];
            graph.computeIfAbsent(mgr, k -> new ArrayList<>()).add(i);
        }

        return dfs(graph, informTime, headID);
    }

    private int dfs(Map<Integer, List<Integer>> graph, int[] informTime, int cur) {
        int max = 0;
        if (!graph.containsKey(cur))
            return max;
        for (int i = 0; i < graph.get(cur).size(); i++)
            max = Math.max(max, dfs(graph, informTime, graph.get(cur).get(i)));

        return max + informTime[cur];
    }
}
