package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph of N edges and a node X is given.
 * The task is to find the level of X. if X does not exist in the graph then print -1.
 *
 * Sample Input :
 * 4 4
 * 0 1
 * 1 2
 * 0 3
 * 1 3
 * 2
 */
public class LevelOfNodes {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String[] st = read.readLine().split(" ");
            int noOfNodes = Integer.parseInt(st[0]);
            int noOfEdges = Integer.parseInt(st[1]);
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();

            for (int i = 0; i < noOfNodes + 1; i++)
                list.add(i, new ArrayList<Integer>());

            for (int i = 1; i <= noOfEdges; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                list.get(u).add(v);
                list.get(v).add(u);
            }

            int x = Integer.parseInt(read.readLine());

            runBFSforNodeLevel(list, noOfNodes, x);
        }
    }

    private static void runBFSforNodeLevel(ArrayList<ArrayList<Integer>> list, int noOfNodes, int x) {
        int sourceVertex = 0;

        boolean[] visited = new boolean[noOfNodes + 1];

        visited[sourceVertex] = true;

        Queue<Integer> queue = new LinkedList<>();
        ((LinkedList<Integer>) queue).add(sourceVertex);

        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                sourceVertex = queue.poll();

                Iterator itr = list.get(sourceVertex).listIterator();

                while (itr.hasNext()) {
                    int currVertex = (int) itr.next();
                    if (currVertex == x) {
                        System.out.println(level);
                        return;
                    }
                    if (!visited[currVertex]) {
                        ((LinkedList<Integer>) queue).add(currVertex);
                        visited[currVertex] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
