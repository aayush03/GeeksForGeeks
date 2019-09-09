package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Count the total number of ways or paths that exist between two vertices in a directed graph. These paths don’t contain a cycle.
 *
 * Sample Input :
 * 1
 * 5 7
 * 1 2 1 3 1 5 2 4 2 5 3 5 4 3
 * 1 5
 */
public class PossiblePathsBetweenTwoVertices {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String[] st = read.readLine().split(" ");

            int N = Integer.parseInt(st[0]);
            int M = Integer.parseInt(st[1]);

            ArrayList<ArrayList<Integer>> list = new ArrayList<>();

            for (int i = 0; i < N + 1; i++)
                list.add(i, new ArrayList<>(M));

            String[] s = read.readLine().split(" ");

            int p = 0;
            for (int i = 0; i < M; i++) {
                int u = Integer.parseInt(s[p++]);
                int v = Integer.parseInt(s[p++]);
                list.get(u).add(v);
            }

            String[] s1 = read.readLine().split(" ");

            int source = Integer.parseInt(s1[0]);
            int dest = Integer.parseInt(s1[1]);

            System.out.println(countPath(list, N, source, dest));

        }
    }

    private static int countPath(ArrayList<ArrayList<Integer>> list, int noOfVertices, int source, int destination) {
        boolean[] visited = new boolean[noOfVertices + 1];

        int pathCount = 0;

        pathCount = countPathUtil(list, source, destination, pathCount, visited);

        return pathCount;
    }

    private static int countPathUtil(ArrayList<ArrayList<Integer>> list, int source, int destination, int pathCount, boolean[] visited) {
        visited[source] = true;

        if (source == destination)
            pathCount++;
        else {
            Iterator itr = list.get(source).listIterator();

            while (itr.hasNext()) {
                int currVertex = (int) itr.next();
                if (!visited[currVertex])
                    pathCount = countPathUtil(list, currVertex, destination, pathCount, visited);
            }
        }

        visited[source] = false;
        return pathCount;
    }
}
