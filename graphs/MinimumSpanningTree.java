package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Aayush Srivastava
 */
public class MinimumSpanningTree {

    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < V; j++) temp.add(Integer.MAX_VALUE);
                graph.add(temp);
            }
            str = read.readLine().trim().split(" ");
            int k = 0;
            int i = 0;
            while (i++ < E) {
                int u = Integer.parseInt(str[k++]);
                int v = Integer.parseInt(str[k++]);
                int w = Integer.parseInt(str[k++]);
                u--;
                v--;
                graph.get(u).set(v, w);
                graph.get(v).set(u, w);
            }

            System.out.println(spanningTree(V, E, graph));
        }
    }

    static int spanningTree(int V, int E, ArrayList<ArrayList<Integer>> graph) {
        // Add your code here
        class Triplet {
            int src;
            int dest;
            int cost;

            public Triplet(int src, int dest, int cost) {
                this.src = src;
                this.dest = dest;
                this.cost = cost;
            }
        }
        PriorityQueue<Triplet> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        int currSrcVertex = 0;
        int noOfEdgesInMST = 0;
        int sum = 0;
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) // not needed for Java
            visited[i] = false;

        while (noOfEdgesInMST != V - 1) {
            visited[currSrcVertex] = true;
            for (int currDest = 0; currDest < V; currDest++) {
                if (graph.get(currSrcVertex).get(currDest) != Integer.MAX_VALUE) {
                    Triplet t = new Triplet(currSrcVertex, currDest, graph.get(currSrcVertex).get(currDest));
                    graph.get(currDest).set(currSrcVertex, Integer.MAX_VALUE);
                    pq.add(t);
                }
            }
            Triplet t = pq.poll();

            while (visited[t.dest])
                t = pq.poll();

            currSrcVertex = t.dest;
            sum += t.cost;
            noOfEdgesInMST++;

        }
        return sum;
    }
}
