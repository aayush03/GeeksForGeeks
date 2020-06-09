package graphs.floyd.warshall;

/**
 * @author Aayush Srivastava
 */
public class AllPairShortestPathUsingFloydWarshall {

    public static void main(String[] args) {
        FloatingWeightMatrixGraph g = new FloatingWeightMatrixGraph(5);

        System.out.println("Graph:");
        // add Edges
        g.addEdge(0, 1, 5.2f);
        g.addEdge(0, 2, 10.3f);
        g.addEdge(0, 3, 12.8f);
        g.addEdge(1, 3, 7.4f);
        g.addEdge(1, 4, 16.2f);
        g.addEdge(2, 1, 1.4f);
        g.addEdge(2, 3, 2.3f);
        g.addEdge(3, 4, 8.5f);
        g.addEdge(4, 2, 2.7f);
        g.addEdge(4, 1, 13.7f);

        // print Graph
        g.printGraph();

        // Floyd-Warshall All Pair Shortest Path Algorithm
        System.out.println("Floyd-Warshall All Pair Shortest Path Matrix:");
        FloydWarshall(g);
    }

    public static void FloydWarshall(FloatingWeightMatrixGraph g) {
        int V = g.getvCount();

        // to store the calculated distances
        float dist[][] = new float[V][V];

        // initialize with adjacency matrix weight values
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = g.getAdj()[i][j];
            }
        }

        // loop through all vertices one by one
        for (int k = 0; k < V; k++) {
            // pick all as source
            for (int i = 0; i < V; i++) {
                // pick all as destination
                for (int j = 0; j < V; j++) {
                    // If k is on the shortest path from i to j
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        // update the value of dist[i][j]
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // shortest path matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // if value is infinity
                if (dist[i][j] == Float.MAX_VALUE)
                    System.out.print("INF \t");
                else
                    System.out.print(dist[i][j] + " \t ");
            }
            System.out.println();
        }

    }
}
